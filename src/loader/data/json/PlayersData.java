/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package loader.data.json;

import java.util.ArrayList;

import loader.data.DataException;
import loader.data.IClosable;

public class PlayersData implements IClosable {
  public class PlayerData extends UnitData implements IClosable {
    public int    startMoney;
    public String startWeapon;
    public Offset handOffset;
  }

  public ArrayList<PlayerData> players;

  @Override
  public void close() throws DataException {
    // No data needs clean up
  }
}
