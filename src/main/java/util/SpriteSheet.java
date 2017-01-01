/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package util;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Utility class for getting sprites from an image.
 */
public class SpriteSheet {
  private final Image mOriginal;
  private final Image[] mTiles;
  private final int mTileWidth;
  private final int mTileHeight;
  private final int mTileCount;

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
    assert original.getWidth() % tileWidth == 0 : "Can not evenly split the image";
    assert original.getHeight() % tileHeight == 0 : "Can not evenly split the image";

    mOriginal = original;
    mTileWidth = tileWidth;
    mTileHeight = tileHeight;

    int tileCountX = original.getWidth() / tileWidth;
    int tileCountY = original.getHeight() / tileHeight;
    mTileCount = tileCountX * tileCountY;

    mTiles = new Image[mTileCount];
    for (int i = 0; i < mTileCount; ++i) {
      int x = i % tileCountX;
      int y = i / tileCountX;

      int px = x * tileWidth;
      int py = y * tileHeight;

      assert px < original.getWidth();
      assert py < original.getHeight();

      mTiles[i] = original.getSubImage(px, py, tileWidth, tileHeight);
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
   * Retrieve a tile.
   * Assumes you are fetching a tile that is not out of bounds.
   *
   * @param i x tile index, in range [0, tileCount]
   * @return the image at the specific tile
   */
  public Image getSubImage(int i) {
    assert i >= 0 && i < mTileCount;
    return mTiles[i];
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

  public int getTileCount() {
    return mTileCount;
  }
}
