/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.interfaces;

import org.newdawn.slick.Graphics;

public interface IRenderComponent extends ILogicComponent {
  void render(Graphics g);

  int getWidth();
  int getHeight();
}
