/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package loader.data.json;

import java.io.IOException;
import java.util.Map;

import loader.IData;
import loader.data.json.types.WeaponData;

public class WeaponsData implements IData {
  public Map<String, WeaponData> weapons;

  @Override
  public void close() throws IOException {
    // No data needs clean up
  }
}
