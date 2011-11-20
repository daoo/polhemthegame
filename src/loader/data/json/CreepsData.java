/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package loader.data.json;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;

import loader.data.DataException;

public class CreepsData implements Closeable {
  public class CreepData extends UnitData implements Closeable {
    public int moneyGain;
    public int damage;
  }

  public ArrayList<CreepData> creeps;

  public CreepData getCreep(String name) throws DataException {
    for (CreepData c : creeps) {
      if (c.name.equals(name)) {
        return c;
      }
    }

    throw new DataException("No creep by the name \"" + name + "\".");
  }

  @Override
  public void close() throws IOException {
    // No data neededs clean up
  }
}
