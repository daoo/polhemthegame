/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package loader.data.json.types;

import java.util.ArrayList;

import loader.data.DataException;

public class UnitData {
  public String                name;
  public Size                  hitbox;
  public ArrayList<SpriteData> sprites;
  public int                   speed;
  public int                   hitpoints;

  public SpriteData getSheet(String animation) throws DataException {
    for (SpriteData s : sprites) {
      if (s.animation.equals(animation)) {
        return s;
      }
    }

    throw new DataException("Sprite " + animation + " not found.");
  }
}
