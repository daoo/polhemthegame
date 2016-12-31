/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.ui.hud;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import game.ui.IStaticUIElement;

public class BlackBox implements IStaticUIElement {
  private final int mX;
  private final int mY;
  private final int mWidth;
  private final int mHeight;

  public BlackBox(int x, int y, int w, int h) {
    mX = x;
    mY = y;
    mWidth = w;
    mHeight = h;
  }

  @Override
  public void render(Graphics g) {
    g.setColor(Color.black);
    g.fillRect(mX, mY, mWidth, mHeight);
  }
}
