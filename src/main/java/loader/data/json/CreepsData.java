/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package loader.data.json;

import java.io.IOException;
import java.util.Map;

import loader.IData;
import loader.data.json.types.CreepData;

@SuppressWarnings("InstanceVariableNamingConvention")
public class CreepsData implements IData {
  private Map<String, CreepData> creeps;

  public CreepData getCreep(String name) {
    return creeps.get(name);
  }

  @Override
  public void close() throws IOException {
    // No data needs clean up
  }
}
