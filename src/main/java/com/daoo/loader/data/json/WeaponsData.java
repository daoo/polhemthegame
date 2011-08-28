/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package com.daoo.loader.data.json;

import java.util.ArrayList;

import com.daoo.loader.data.DataException;
import com.daoo.loader.data.IClosable;

public class WeaponsData implements IClosable {
  public class WeaponData {
    public String     name;

    public boolean    automatic;
    public int        clipSize;
    public float      rpm;
    public float      reloadTime;
    public float      launchAngle;

    public SpriteData sprite;
    public String     texture;

    public String     projectile;
    public Offset     muzzleOffset;
  }

  public ArrayList<WeaponData> weapons;

  public WeaponData getWeapon(final String name) throws DataException {
    for (final WeaponData w : weapons) {
      if (w.name.equals(name)) {
        return w;
      }
    }

    throw new DataException("No weapon by the name \"" + name + "\".");
  }

  @Override
  public void close() throws DataException {
    // No data needs clean up
  }
}
