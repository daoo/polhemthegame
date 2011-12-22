/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package states;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import debug.IDebuggable;

public interface IState extends IDebuggable {
  void start(StateManager stateManager);
  void end(StateManager stateManager);

  void render(Graphics g) throws SlickException;

  /**
   * Logic part of the game loop.
   * @param stateGame the game state manager
   * @param delta frame length in seconds
   */
  void update(StateManager stateGame, float delta);
}
