/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package loader.data.json;

import java.io.IOException;
import java.util.Map;

import loader.IData;
import loader.data.json.types.PlayerData;

@SuppressWarnings("InstanceVariableNamingConvention")
public class PlayersData implements IData {
  private Map<String, PlayerData> players;

  public PlayerData getPlayer(String name) {
    return players.get(name);
  }

  @Override
  public void close() throws IOException {
    // No data needs clean up
  }
}
