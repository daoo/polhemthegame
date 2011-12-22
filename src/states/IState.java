/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package states;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import util.Node;

public interface IState {
  void start(StateManager stateManager);
  void end(StateManager stateManager);

  void render(Graphics g) throws SlickException;

  /**
   * Logic part of the game loop.
   * @param stateGame the game state manager
   * @param delta frame length in seconds
   */
  void update(StateManager stateGame, float delta);

  /**
   * Returns a string containing information about the current game state.
   * @return a string with debugging information
   */
  public Node<Object> debugInfo();
}
