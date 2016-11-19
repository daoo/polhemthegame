/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package loader.data.json;

import java.io.IOException;
import java.util.ArrayList;

import loader.IData;
import loader.data.json.types.CreepSpawnData;

public class LevelData implements IData {
  public String level;
  public String loading, completed, preBossImage;

  public String boss;
  public ArrayList<CreepSpawnData> creeps;

  @Override
  public void close() throws IOException {
    // No data needs clean up
  }
}
