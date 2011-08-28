/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package loader.data.json;

import java.util.ArrayList;

import loader.data.DataException;
import loader.data.IClosable;

public class BossesData implements IClosable {
  public class BossData extends UnitData implements IClosable {
    public int    locationX;
    public String weapon;
    public float  fireLength;
  }

  public ArrayList<BossData> bosses;

  public BossData getBoss(final String name) throws DataException {
    for (final BossData d : bosses) {
      if (d.name.equals(name)) {
        return d;
      }
    }

    throw new DataException("No boss by the name \"" + name + "\".");
  }

  @Override
  public void close() throws DataException {
    // Do nothing
  }
}
