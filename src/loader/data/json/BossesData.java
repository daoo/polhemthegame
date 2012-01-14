/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package loader.data.json;

import java.io.IOException;
import java.util.ArrayList;

import loader.IData;
import loader.data.DataException;
import loader.data.json.types.BossData;

public class BossesData implements IData {
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
