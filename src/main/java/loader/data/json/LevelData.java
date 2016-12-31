/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package loader.data.json;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;

import loader.data.json.types.CreepSpawnData;

@SuppressWarnings("InstanceVariableNamingConvention")
public class LevelData implements Closeable {
  public String level;
  public String loading;
  public String completed;
  public String preBossImage;

  public String boss;
  public ArrayList<CreepSpawnData> creeps;

  @Override
  public void close() throws IOException {
    // No data needs clean up
  }
}
