/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package com.daoo.game;

import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.daoo.components.triggers.actions.IAction;
import com.daoo.entities.Players;
import com.daoo.factories.Factory;
import com.daoo.game.states.BossState;
import com.daoo.game.states.CreepsState;
import com.daoo.game.states.DoubleState;
import com.daoo.game.states.IState;
import com.daoo.game.states.StateIterator;
import com.daoo.game.states.TransitionState;
import com.daoo.loader.data.DataException;
import com.daoo.loader.data.json.LevelData;
import com.daoo.loader.data.json.LevelData.BossStateData;
import com.daoo.loader.data.json.LevelData.CreepStateData;
import com.daoo.loader.data.json.LevelData.StateData;
import com.daoo.loader.data.json.LevelData.TextStateData;
import com.daoo.loader.parser.ParserException;
import com.daoo.math.Rectangle;
import com.daoo.math.time.GameTime;
import com.daoo.ptg.CacheTool;
import com.daoo.ptg.Locator;

public class GameLevel {
  /**
   * The world, rendering is happening relative to what's actually availible.
   * I.e. all constraints, HUD stuff etc have been accounted for. Adding something at
   * (0, 0) would make it appear in the top left of the area units are allowed to
   * move around in.
   */
  private final World             world;
  private final Image             background;
  private final Players           players;

  /**
   * The area availible for the current level.
   * E.g. with constraints for the active level. Relative to the visible background.
   */
  private final Rectangle         rect;

  /**
   * The area actually used.
   * Has the same size as rect but top left will always be (0, 0).
   */
  private final Rectangle         availible;

  private final DoubleState       gameOverState;
  private final ArrayList<IState> states;
  private final StateIterator     current;

  public GameLevel(final LevelData level, final Players players,
                   final float width, final float height,
                   final DoubleState gameOverState)
    throws DataException, IOException, ParserException {
    // World and it's rectangles
    final float left   = level.constraints[0];
    final float top    = level.constraints[1];
    final float bottom = level.constraints[2];
    final float right  = level.constraints[3];

    rect      = new Rectangle(left , top , width - left - right , height - top - bottom);
    availible = new Rectangle(0    , 0   , rect.getWidth()      , rect.getHeight());

    background = CacheTool.getImage(Locator.getCache(), level.background);
    world      = Factory.MakeWorld(availible, players);

    // Players
    this.players = players;
    players.reposition(availible);

    // Setup level and it's states
    this.states        = new ArrayList<IState>();
    this.gameOverState = gameOverState;

    for (final String state : level.states) {
      final StateData sd = level.findState(state);

      if (sd.type.equals("text")) {
        TextStateData data = (TextStateData) sd;
        states.add(new TransitionState(
          CacheTool.getImage(Locator.getCache(), data.text),
          data.duration, availible));
      } else if (sd.type.equals("creeps")) {
        states.add(new CreepsState(availible, (CreepStateData) sd));
      } else if (sd.type.equals("boss")) {
        states.add(new BossState(availible, (BossStateData) sd));
      }
    }
    
    current = new StateIterator(states);
  }

  public void render(final Graphics g) {
    g.drawImage(background, 0, 0);

    g.pushTransform();
    g.translate(rect.getX1(), rect.getY1());

    world.render(g);
    
    current.getCurrent().render(g);

    g.popTransform();
  }

  public void update(final GameTime time) {
    if (current.getCurrent().isFinished()) {
      nextState();
    } else {
      current.getCurrent().update(time);

      world.update(time);

      if (current.getCurrent().hasActions()) {
        for (IAction action : current.getCurrent().getActions()) {
          action.execute(time, world);
        }
        current.getCurrent().clearActions();
      }

      if (!players.isAlive() && !current.isFinished()) {
        final IState oldState = current.getCurrent();

        current.endNowWith(gameOverState);
        gameOverState.start(oldState);
      }
    }
  }

  public void start() {
    nextState();
  }

  public boolean isFinished() {
    return current.isFinished()
        || (current.getCurrent().isFinished() && !current.hasNext());
  }

  private void nextState() {
    if (current.hasNext()) {
      current.next();
    }
  }
}
