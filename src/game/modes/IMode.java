/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.modes;

import org.newdawn.slick.Graphics;

import states.StateManager;

public interface IMode {
  void update(StateManager stateManager, float dt);
  void render(Graphics g);
}
