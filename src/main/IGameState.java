/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package main;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public interface IGameState {
  void render(final Graphics g) throws SlickException;

  /**
   * Logic part of the game loop.
   * @param stateGame the game state manager
   * @param delta frame length in seconds
   */
  void update(final GameStateManager stateGame, float delta);
}
