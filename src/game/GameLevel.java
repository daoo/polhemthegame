/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game;

import java.io.IOException;
import java.util.ArrayList;

import loader.data.DataException;
import loader.data.json.LevelData;
import loader.data.json.LevelData.BossStateData;
import loader.data.json.LevelData.CreepStateData;
import loader.data.json.LevelData.StateData;
import loader.data.json.LevelData.TextStateData;
import loader.parser.ParserException;
import main.CacheTool;
import main.Locator;
import math.Rectangle;
import math.time.GameTime;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import components.triggers.actions.IAction;

import entities.Players;
import factories.WorldFactory;
import game.states.BossState;
import game.states.CreepsState;
import game.states.DoubleState;
import game.states.IState;
import game.states.StateIterator;
import game.states.TransitionState;

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
    world      = WorldFactory.make(availible, players);

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
          action.execute(world);
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
