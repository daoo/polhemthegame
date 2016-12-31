/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics.debug;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import game.components.IRenderComponent;
import game.types.GameTime;
import game.types.Message;

public class SolidQuad implements IRenderComponent {
  private final Color mColor;
  private final int mWidth;
  private final int mHeight;

  public SolidQuad(Color color, int width, int height) {
    mColor = color;
    mWidth = width;
    mHeight = height;
  }

  @Override
  public int getWidth() {
    return mWidth;
  }

  @Override
  public int getHeight() {
    return mHeight;
  }

  @Override
  public void update(GameTime time) {
    // Do nothing
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    // Do nothing
  }

  @Override
  public void render(Graphics g) {
    g.setColor(mColor);
    g.fillRect(0, 0, mWidth, mHeight);
  }

}
