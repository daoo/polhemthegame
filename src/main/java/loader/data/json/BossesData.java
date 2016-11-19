/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package loader.data.json;

import java.io.IOException;
import java.util.Map;

import loader.IData;
import loader.data.json.types.BossData;

public class BossesData implements IData {
  private Map<String, BossData> bosses;

  public BossData getBoss(String name) {
    return bosses.get(name);
  }

  @Override
  public void close() throws IOException {
    // Do nothing
  }
}
