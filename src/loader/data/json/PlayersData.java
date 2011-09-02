/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package com.daoo.loader.data.json;

import java.util.ArrayList;

import com.daoo.loader.data.DataException;
import com.daoo.loader.data.IClosable;

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
