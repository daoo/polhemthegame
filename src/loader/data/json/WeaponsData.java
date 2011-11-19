/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package loader.data.json;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;

import loader.data.DataException;

public class WeaponsData implements Closeable {
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

  public WeaponData getWeapon(String name) throws DataException {
    for (WeaponData w : weapons) {
      if (w.name.equals(name)) {
        return w;
      }
    }

    throw new DataException("No weapon by the name \"" + name + "\".");
  }

  @Override
  public void close() throws IOException {
    // No data needs clean up
  }
}
