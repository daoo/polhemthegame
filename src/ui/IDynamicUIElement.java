/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package ui;

import org.newdawn.slick.Graphics;

public interface IDynamicUIElement {
  void update();
  void render(Graphics g);
  
  boolean isActive();
}
