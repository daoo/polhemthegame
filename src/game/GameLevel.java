/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import loader.data.DataException;
import loader.data.json.LevelData;
import loader.data.json.LevelData.BossStateData;
import loader.data.json.LevelData.CreepStateData;
import loader.data.json.LevelData.StateData;
import loader.data.json.LevelData.TextStateData;
import loader.parser.ParserException;
import main.CacheTool;
import main.Launcher;
import math.Rectangle;
import math.time.GameTime;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import components.triggers.actions.IAction;

import entities.Players;
import factories.WorldFactory;
import game.states.BossState;
import game.states.CreepsState;
import game.states.ICompState;
import game.states.TransitionState;

public class GameLevel {
  /**
   * The world, rendering is happening relative to what's actually availible.
   * I.e. all constraints, HUD stuff etc have been accounted for. Adding something at
   * (0, 0) would make it appear in the top left of the area units are allowed to
   * move around in.
   */
  private final World                 world;
  private final Image                 background;

  /**
   * The area availible for the current level.
   * E.g. with constraints for the active level. Relative to the visible background.
   */
  private final Rectangle             rect;

  /**
   * The area actually used.
   * Has the same size as rect but top left will always be (0, 0).
   */
  private final Rectangle             availible;

  private final ArrayList<ICompState> states;
  private Iterator<ICompState>        current;
  private ICompState                  currentState;

  public GameLevel(final LevelData level, final Players players,
                   final float width, final float height)
    throws DataException, IOException, ParserException {
    final float left   = level.constraints[0];
    final float top    = level.constraints[1];
    final float bottom = level.constraints[2];
    final float right  = level.constraints[3];

    rect = new Rectangle(left, top, width - left - right, height - top - bottom);
    availible = new Rectangle(0, 0, rect.getWidth(), rect.getHeight());

    background = CacheTool.getImage(Launcher.cache, level.background);
    world = WorldFactory.make(availible, players);
    players.reposition(availible);

    // Setup level and it's states
    states = new ArrayList<ICompState>();
    for (final String state : level.states) {
      final StateData sd = level.findState(state);
      if (sd.type.equals("text")) {
        states.add(new TransitionState((TextStateData) sd, Launcher.RECT));
      } else if (sd.type.equals("creeps")) {
        states.add(new CreepsState(availible, (CreepStateData) sd));
      } else if (sd.type.equals("boss")) {
        states.add(new BossState(availible, (BossStateData) sd));
      }
    }
  }

  public void render(final Graphics g) {
    g.drawImage(background, 0, 0);

    g.pushTransform();
    g.translate(rect.getX1(), rect.getY1());

    world.render(g);
    currentState.render(g);

    g.popTransform();
  }

  public void update(final GameTime time) {
    if (currentState.isFinished()) {
      if (current.hasNext()) {
        nextState();
      }
    } else {
      currentState.update(time);
      world.update(time);

      if (currentState.hasActions()) {
        for (IAction action : currentState.getActions()) {
          action.execute(world);
        }
        currentState.clearActions();
      }
    }
  }

  public void start() {
    if (!states.isEmpty()) {
      current = states.iterator();
      nextState();
    }
  }

  public boolean isFinished() {
    return ((currentState == null) || currentState.isFinished())
           && !current.hasNext();
  }

  private void nextState() {
    currentState = current.next();
    currentState.start();
  }
}
