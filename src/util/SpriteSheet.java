/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package util;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class SpriteSheet {
  private final Image original;
  private final Image[][] subImages;

  private final int tileWidth, tileHeight;

  private final int tileCountX, tileCountY;

  /**
   * @param original
   * @param tileWidth
   * @param tileHeight
   * @param spacing
   */
  public SpriteSheet(Image original, int tileWidth, int tileHeight, int spacing) {
    this.original = original;

    assert original.getWidth() % tileWidth == 0;
    assert original.getHeight() % tileHeight == 0;

    this.tileWidth  = tileWidth;
    this.tileHeight = tileHeight;

    tileCountX = count(original.getWidth(), tileWidth, spacing);
    tileCountY = count(original.getHeight(), tileHeight, spacing);

    subImages = new Image[tileCountX][tileCountY];
    for (int y = 0; y < tileCountY; ++y) {
      for (int x = 0; x < tileCountX; ++x) {
        subImages[x][y] = getSubImage(original, x, y, tileWidth, tileHeight, spacing);
      }
    }
  }

  public void destroy() throws SlickException {
    original.destroy();
  }

  public void render(Graphics g, int tx, int ty, float x, float y) {
    g.drawImage(subImages[tx][ty], x, y);
  }

  public Image getSubImage(int x, int y) {
    assert x < tileCountX;
    assert y < tileCountY;

    return subImages[x][y];
  }

  public int getTileWidth() {
    return tileWidth;
  }

  public int getTileHeight() {
    return tileHeight;
  }

  public int getTileCountX() {
    return tileCountX;
  }

  public int getTileCountY() {
    return tileCountY;
  }

  private static int count(int i, int t, int s) {
    return (i - s) / (s + t);
  }

  private static Image getSubImage(Image img, int x, int y, int tw, int th, int s) {
    int px = x * s + x * tw;
    int py = y * s + y * th;

    assert px < img.getWidth();
    assert py < img.getHeight();

    return img.getSubImage(px, py, tw, th);
  }
}
