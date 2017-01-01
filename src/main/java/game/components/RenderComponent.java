/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components;

import org.newdawn.slick.Graphics;

public interface RenderComponent extends LogicComponent {
  void render(Graphics g);
}
