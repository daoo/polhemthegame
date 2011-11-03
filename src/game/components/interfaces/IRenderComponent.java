/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.interfaces;

import org.newdawn.slick.Graphics;

public interface IRenderComponent extends ILogicComponent {
  void render(Graphics g);
}
