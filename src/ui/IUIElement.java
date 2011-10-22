/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package ui;

import org.newdawn.slick.Graphics;

public interface IUIElement {
  void update();
  void render(Graphics g);
}
