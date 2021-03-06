/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package loader.data.json;

import java.io.Closeable;
import java.io.IOException;
import java.util.Map;

import loader.data.json.types.BossData;

@SuppressWarnings("InstanceVariableNamingConvention")
public class BossesData implements Closeable {
  private Map<String, BossData> bosses;

  public BossData getBoss(String name) {
    return bosses.get(name);
  }

  @Override
  public void close() throws IOException {
    // Do nothing
  }
}
