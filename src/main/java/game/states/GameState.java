/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.states;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import debug.Debuggable;

public interface GameState extends Debuggable {
  void start(StateManager stateManager);

  void end(StateManager stateManager);

  void render(Graphics g) throws SlickException;

  /**
   * Logic part of the game loop.
   *
   * @param stateGame the game state manager
   * @param delta frame length in milliseconds
   */
  void update(StateManager stateGame, int delta);
}
