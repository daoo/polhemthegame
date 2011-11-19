/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package loader.data.json;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;

public class LevelData implements Closeable {
  public class CreepSpawnData {
    public String creep;
    public float  spawnTime;
  }

  public String level;
  public int[]  constraints;
  public String background;
  public String loading, completed, preBossImage;

  public String                    boss;
  public ArrayList<CreepSpawnData> creeps;

  @Override
  public void close() throws IOException {
    // No data needs clean up
  }

  @Override
  public String toString() {
    return level;
  }
}
