/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package loader.data.json;

import java.util.ArrayList;

import loader.data.DataException;
import loader.data.IClosable;

public class UnitData implements IClosable {
  public String                name;
  public int[]                 hitBox;
  public ArrayList<SpriteData> sprites;
  public int                   speed;
  public int                   hitPoints;

  @Override
  public void close() throws DataException {
    sprites.clear();
  }

  public SpriteData getSheet(final String animation) throws DataException {
    for (final SpriteData s : sprites) {
      if (s.animation.equals(animation)) {
        return s;
      }
    }

    throw new DataException("Sprite " + animation + " not found.");
  }
}
