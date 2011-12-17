/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.modes;

import org.newdawn.slick.Graphics;

import states.StateManager;

public interface IMode {
  void start(StateManager stateManager);

  /**
   * Update frame.
   * @param stateManager the state manager for current state
   * @param dt time in seconds
   */
  void update(StateManager stateManager, float dt);
  void render(Graphics g);
}
