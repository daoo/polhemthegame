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
  private final Image mOriginal;
  private final Image[][] mSubImages;

  private final int mTileWidth;
  private final int mTileHeight;

  private final int mTileCountX;
  private final int mTileCountY;

  /**
   * Create a new sprite sheet.
   * Note that this will not create a copy of the image. Changes to the image
   * will affect the sprite sheet.
   *
   * @param original the image to get the sprites from
   * @param tileWidth the width of a tile in the sheet, greater than zero, also
   * has to evenly split the image
   * @param tileHeight the height of a tile in the sheet, greater than zero, also
   * has to evenly split the image
   */
  public SpriteSheet(Image original, int tileWidth, int tileHeight) {
    assert tileWidth > 0 && tileHeight > 0;
    assert tileWidth <= original.getWidth();
    assert tileHeight <= original.getHeight();

    mOriginal = original;
    mTileWidth = tileWidth;
    mTileHeight = tileHeight;

    mTileCountX = count(original.getWidth(), tileWidth);
    mTileCountY = count(original.getHeight(), tileHeight);

    mSubImages = new Image[mTileCountX][mTileCountY];
    for (int y = 0; y < mTileCountY; ++y) {
      for (int x = 0; x < mTileCountX; ++x) {
        mSubImages[x][y] = getSubImage(original, x, y, tileWidth, tileHeight);
      }
    }
  }

  /**
   * Calls destroy on the image.
   *
   * @throws SlickException @see Image.destroy()
   */
  public void destroy() throws SlickException {
    mOriginal.destroy();
  }

  /**
   * Render a certain tile to a graphics context.
   * Assumes that you try to render a tile that is not out of bounds.
   *
   * @param g the graphics context
   * @param tx x tile index, in range [0, tileCountX]
   * @param ty y tile index, in range [0, tileCountY]
   * @param x x offset when rendering
   * @param y y offset when rendering
   */
  public void render(Graphics g, int tx, int ty, float x, float y) {
    assert tx >= 0 && x < mTileCountX;
    assert ty >= 0 && y < mTileCountY;

    g.drawImage(mSubImages[tx][ty], x, y);
  }

  /**
   * Retrieve a tile.
   * Assumes you are fetching a tile that is not out of bounds.
   *
   * @param x x tile index, in range [0, tileCountX]
   * @param y y tile index, in range [0, tileCountY]
   * @return the image at the specific tile
   */
  public Image getSubImage(int x, int y) {
    assert x >= 0 && x < mTileCountX;
    assert y >= 0 && y < mTileCountY;

    return mSubImages[x][y];
  }

  /**
   * Get the width of a tile.
   *
   * @return the width of a tile, int greater than zero
   */
  public int getTileWidth() {
    return mTileWidth;
  }

  /**
   * Get the height of a tile.
   *
   * @return the height of a tile, int greater than zero
   */
  public int getTileHeight() {
    return mTileHeight;
  }

  /**
   * Get the number of horizontal tiles.
   *
   * @return number of horizontal tiles, int greater than zero
   */
  public int getTileCountX() {
    return mTileCountX;
  }

  /**
   * Get the number of vertical tiles.
   *
   * @return number of vertical tiles, int greater than zero
   */
  public int getTileCountY() {
    return mTileCountY;
  }

  /**
   * Returns the number of tiles we could fit.
   *
   * @param i the image size (width or height), greater than zero
   * @param t the tile size (width or height), greater than zero
   */
  private static int count(int i, int t) {
    assert i > 0 && t > 0;
    assert i % t == 0 : "Could not evenly split the image";

    return i / t;
  }

  /**
   * Return the sub image with a specific index.
   *
   * @param img the image to cut from
   * @param x x index
   * @param y y index
   * @param tw tile width
   * @param th tile height
   */
  private static Image getSubImage(Image img, int x, int y, int tw, int th) {
    assert x >= 0 && y >= 0;
    assert tw > 0 && th > 0;

    int px = x * tw;
    int py = y * th;

    assert px < img.getWidth();
    assert py < img.getHeight();

    return img.getSubImage(px, py, tw, th);
  }
}
