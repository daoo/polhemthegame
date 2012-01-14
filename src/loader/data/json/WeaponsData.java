/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package loader.data.json;

import java.io.IOException;
import java.util.Map;

import loader.IData;
import loader.data.json.types.WeaponData;

public class WeaponsData implements IData {
  private Map<String, WeaponData> weapons;

  public WeaponData getWeapon(String name) {
    return weapons.get(name);
  }

  @Override
  public void close() throws IOException {
    // No data needs clean up
  }
}
