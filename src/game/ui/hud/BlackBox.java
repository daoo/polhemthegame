/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.ui.hud;

import game.ui.IStaticUIElement;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;


public class BlackBox implements IStaticUIElement {
  private final int x, y, w, h;

  public BlackBox(int x, int y, int w, int h) {
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
  }

  @Override
  public void render(Graphics g) {
    g.setColor(Color.black);
    g.fillRect(x, y, w, h);
  }
}
