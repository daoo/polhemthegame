/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package loader.data;

import java.io.InputStream;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class ClosableSpriteSheet extends SpriteSheet implements IClosable {
  public ClosableSpriteSheet(String name, InputStream ref, int tw, int th)
      throws SlickException {
    super(name, ref, tw, th);
  }

  @Override
  public void close() throws DataException {
    try {
      destroy();
    } catch (SlickException e) {
      throw new DataException(e);
    }
  }
}
