/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package loader.data.json;

import java.util.ArrayList;

import loader.data.DataException;
import loader.data.IClosable;

public class PlayersData implements IClosable {
  public class PlayerData extends UnitData implements IClosable {
    public int    startMoney;
    public String startWeapon;
    public int[]  handOffset;
  }

  public ArrayList<PlayerData> players;

  @Override
  public void close() throws DataException {
    // No data needs clean up
  }
}
