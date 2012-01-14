/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package loader.data.json;

import java.io.IOException;
import java.util.ArrayList;

import loader.IData;
import loader.data.DataException;
import loader.data.json.types.WeaponData;

public class WeaponsData implements IData {
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
