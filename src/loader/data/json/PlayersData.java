/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package loader.data.json;

import java.io.IOException;
import java.util.ArrayList;

import loader.IData;
import loader.data.json.types.PlayerData;

public class PlayersData implements IData {
  public ArrayList<PlayerData> players;

  @Override
  public void close() throws IOException {
    // No data needs clean up
  }
}
