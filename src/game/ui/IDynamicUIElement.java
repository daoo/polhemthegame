/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.ui;

import org.newdawn.slick.Graphics;

public interface IDynamicUIElement {
  void update();
  void render(Graphics g);

  boolean isActive();
}
