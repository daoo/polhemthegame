/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package loader.data.json;

import loader.data.DataException;
import loader.data.IClosable;

public class SpriteData implements IClosable {
  public String animation;
  public String sprite;
  public Size   tileSize;
  public int    framerate;
  public Offset offset;

  @Override
  public void close() throws DataException {
    // No data needs clean up
  }
}
