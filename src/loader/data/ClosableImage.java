/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package loader.data;

import java.io.InputStream;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ClosableImage extends Image implements IClosable {
  public ClosableImage(final InputStream is, final String string,
                       final boolean b) throws SlickException {
    super(is, string, b);
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
