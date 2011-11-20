/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package loader.data.json;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;

public class PlayersData implements Closeable {
  public class PlayerData extends UnitData implements Closeable {
    public int    startMoney;
    public String startWeapon;
    public Offset handOffset;
  }

  public ArrayList<PlayerData> players;

  @Override
  public void close() throws IOException {
    // No data needs clean up
  }
}
