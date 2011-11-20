/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package loader.data.json;

import java.io.Closeable;
import java.io.IOException;

public class SpriteData implements Closeable {
  public String animation;
  public String sprite;
  public Size   tileSize;
  public int    framerate;
  public Offset offset;

  @Override
  public void close() throws IOException {
    // No data needs clean up
  }
}
