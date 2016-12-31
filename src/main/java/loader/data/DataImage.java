/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package loader.data;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

public class DataImage extends Image implements Closeable {
  public DataImage(InputStream is, String string, boolean b) throws SlickException {
    super(is, string, b);
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
