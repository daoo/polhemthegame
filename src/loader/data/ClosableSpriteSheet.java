/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package loader.data;

import java.io.InputStream;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class ClosableSpriteSheet extends SpriteSheet implements IClosable {
  public ClosableSpriteSheet(final String name, final InputStream ref, final int tw, final int th)
    throws SlickException {
    super(name, ref, tw, th);
  }

  @Override
  public void close() throws DataException {
    try {
      destroy();
    } catch (final SlickException e) {
      throw new DataException(e);
    }
  }
}
