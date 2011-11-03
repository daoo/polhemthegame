/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package ui;

import org.newdawn.slick.Graphics;

public interface IUI {
  void addElement(IUIElement element);
  void removeElement(IUIElement element);
  void clearElements();

  void update();
  void render(Graphics g);
}
