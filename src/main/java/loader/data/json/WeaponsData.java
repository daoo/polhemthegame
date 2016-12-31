/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package loader.data.json;

import java.io.Closeable;
import java.io.IOException;
import java.util.Map;

import loader.data.json.types.WeaponData;

@SuppressWarnings("InstanceVariableNamingConvention")
public class WeaponsData implements Closeable {
  public Map<String, WeaponData> weapons;

  @Override
  public void close() throws IOException {
    // No data needs clean up
  }
}
