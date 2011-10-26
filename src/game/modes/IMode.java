/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.modes;

import org.newdawn.slick.Graphics;

public interface IMode {
  void update(float dt);
  void render(Graphics g);
}
