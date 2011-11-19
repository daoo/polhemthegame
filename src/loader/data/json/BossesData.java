/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package loader.data.json;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;

import loader.data.DataException;

public class BossesData implements Closeable {
  public class BossData extends UnitData implements Closeable {
    public int    locationX;
    public String weapon;
    public float  fireLength;
  }

  public ArrayList<BossData> bosses;

  public BossData getBoss(String name) throws DataException {
    for (BossData d : bosses) {
      if (d.name.equals(name)) {
        return d;
      }
    }

    throw new DataException("No boss by the name \"" + name + "\".");
  }

  @Override
  public void close() throws IOException {
    // Do nothing
  }
}
