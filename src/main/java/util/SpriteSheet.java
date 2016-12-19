/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package util;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Utility class for getting sprites from an image.
 */
public class SpriteSheet {
  private final Image original;
  private final Image[][] subImages;

  private final int tileWidth;
  private final int tileHeight;

  private final int tileCountX;
  private final int tileCountY;

  /**
   * Create a new sprite sheet.
   * Note that this will not create a copy of the image. Changes to the image
   * will affect the sprite sheet.
   * @param original the image to get the sprites from
   * @param tileWidth the width of a tile in the sheet, greater than zero, also
   *                  has to evenly split the image with the spacing
   * @param tileHeight the height of a tile in the sheet, greater than zero, also
   *                   has to evenly split the image with the spacing
   * @param spacing spacing between the tiles, greater than or equal to zero
   */
  public SpriteSheet(Image original, int tileWidth, int tileHeight, int spacing) {
    assert tileWidth > 0 && tileHeight > 0;
    assert tileWidth <= original.getWidth();
    assert tileHeight <= original.getHeight();
    assert spacing >= 0;

    this.original   = original;
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

  /**
   * Calls destroy on the image.
   * @throws SlickException @see Image.destroy()
   */
  public void destroy() throws SlickException {
    original.destroy();
  }

  /**
   * Render a certain tile to a graphics context.
   * Assumes that you try to render a tile that is not out of bounds.
   * @param g the graphics context
   * @param tx x tile index, in range [0, tileCountX]
   * @param ty y tile index, in range [0, tileCountY]
   * @param x x offset when rendering
   * @param y y offset when rendering
   */
  public void render(Graphics g, int tx, int ty, float x, float y) {
    assert tx >= 0 && x < tileCountX;
    assert ty >= 0 && y < tileCountY;

    g.drawImage(subImages[tx][ty], x, y);
  }

  /**
   * Retrieve a tile.
   * Assumes you are fetching a tile that is not out of bounds.
   * @param x x tile index, in range [0, tileCountX]
   * @param y y tile index, in range [0, tileCountY]
   * @return the image at the specific tile
   */
  public Image getSubImage(int x, int y) {
    assert x >= 0 && x < tileCountX;
    assert y >= 0 && y < tileCountY;

    return subImages[x][y];
  }

  /**
   * Get the width of a tile.
   * @return the width of a tile, int greater than zero
   */
  public int getTileWidth() {
    return tileWidth;
  }

  /**
   * Get the height of a tile.
   * @return the height of a tile, int greater than zero
   */
  public int getTileHeight() {
    return tileHeight;
  }

  /**
   * Get the number of horizontal tiles.
   * @return number of horizontal tiles, int greater than zero
   */
  public int getTileCountX() {
    return tileCountX;
  }

  /**
   * Get the number of vertical tiles.
   * @return number of vertical tiles, int greater than zero
   */
  public int getTileCountY() {
    return tileCountY;
  }

  /**
   * Returns the number of tiles we could fit.
   * @param i the image size (width or height), greater than zero
   * @param t the tile size (width or height), greater than zero
   * @param s the spacing, greater than or equal to zero
   */
  private static int count(int i, int t, int s) {
    assert i > 0 && t > 0 && s >= 0;
    assert (i - s) % (s + t) == 0 : "Could not evenly split the image";

    return (i - s) / (s + t);
  }

  /**
   * Return the sub image with a specific index.
   * @param img the image to cut from
   * @param x x index
   * @param y y index
   * @param tw tile width
   * @param th tile height
   * @param s spacing
   */
  private static Image getSubImage(Image img, int x, int y, int tw, int th, int s) {
    assert x >= 0 && y >= 0;
    assert tw > 0 && th > 0;
    assert s >= 0;

    int px = x * s + x * tw;
    int py = y * s + y * th;

    assert px < img.getWidth();
    assert py < img.getHeight();

    return img.getSubImage(px, py, tw, th);
  }
}
