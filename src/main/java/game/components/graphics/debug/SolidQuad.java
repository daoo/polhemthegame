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
  private final Color color;
  private final int width;
  private final int height;

  public SolidQuad(Color color, int width, int height) {
    this.color = color;
    this.width = width;
    this.height = height;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
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
    g.setColor(color);
    g.fillRect(0, 0, width, height);
  }

}
