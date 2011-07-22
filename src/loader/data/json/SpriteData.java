/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package loader.data.json;

import loader.data.DataException;
import loader.data.IClosable;

public class SpriteData implements IClosable {
  public String animation;
  public String sprite;
  public int[]  tileSize;
  public int    framerate;
  public int[]  offset;

  @Override
  public void close() throws DataException {}
}
