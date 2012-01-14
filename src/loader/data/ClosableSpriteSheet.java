/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package loader.data;

import java.io.IOException;
import java.io.InputStream;

import loader.IData;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class ClosableSpriteSheet extends SpriteSheet implements IData {
  public ClosableSpriteSheet(String name, InputStream ref, int tw, int th)
      throws SlickException {
    super(name, ref, tw, th);
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
