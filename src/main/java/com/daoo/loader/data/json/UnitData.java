/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package com.daoo.loader.data.json;

import java.util.ArrayList;

import com.daoo.loader.data.DataException;
import com.daoo.loader.data.IClosable;

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

  public SpriteData getSheet(final String animation) throws DataException {
    for (final SpriteData s : sprites) {
      if (s.animation.equals(animation)) {
        return s;
      }
    }

    throw new DataException("Sprite " + animation + " not found.");
  }
}
