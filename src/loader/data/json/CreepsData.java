/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package loader.data.json;

import java.util.ArrayList;

import loader.data.DataException;
import loader.data.IClosable;

public class CreepsData implements IClosable {
  public class CreepData extends UnitData implements IClosable {
    public int moneyGain;
    public int damage;
  }

  public ArrayList<CreepData> creeps;

  public CreepData getCreep(final String name) throws DataException {
    for (final CreepData c : creeps) {
      if (c.name.equals(name)) {
        return c;
      }
    }

    throw new DataException("No creep by the name \"" + name + "\".");
  }

  @Override
  public void close() throws DataException {
    // No data neededs clean up
  }
}
