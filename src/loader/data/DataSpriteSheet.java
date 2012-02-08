/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package loader.data;

import java.io.IOException;

import loader.IData;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import util.SpriteSheet;

public class DataSpriteSheet extends SpriteSheet implements IData {
  public DataSpriteSheet(Image original, int tileWidth, int tileHeight, int spacing) {
    super(original, tileWidth, tileHeight, spacing);
  }

  @Override
  public void close() throws IOException {
    try {
      destroy();
    } catch (SlickException e) {
      throw new IOException(e);
    }
  }
}
