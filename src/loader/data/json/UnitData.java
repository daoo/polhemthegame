/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package loader.data.json;

import java.util.ArrayList;

import loader.data.DataException;
import loader.data.IClosable;

public class UnitData implements IClosable {
  public String                name;
  public Size                  hitbox;
  public ArrayList<SpriteData> sprites;
  public int                   speed;
  public int                   hitpoints;

  @Override
  public void close() throws DataException {
    sprites.clear();
  }

  public SpriteData getSheet(String animation) throws DataException {
    for (SpriteData s : sprites) {
      if (s.animation.equals(animation)) {
        return s;
      }
    }

    throw new DataException("Sprite " + animation + " not found.");
  }
}
