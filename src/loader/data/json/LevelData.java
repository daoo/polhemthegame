/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package loader.data.json;

import java.util.ArrayList;

import loader.data.DataException;
import loader.data.IClosable;

public class LevelData implements IClosable {
  public class CreepSpawnData {
    public String creep;
    public float  spawnTime;
  }

  public class CreepStateData extends StateData {
    public ArrayList<CreepSpawnData> creeps;
  }

  public class TextStateData extends StateData {
    public String text;
    public float  duration;
  }

  public class BossStateData extends StateData {
    String boss;
  }

  public class StateData {
    public String state;
    public String type;
  }

  public String                    level;
  public int[]                     constraints;
  public String                    background;
  public ArrayList<String>         states;
  public ArrayList<TextStateData>  textStates;
  public ArrayList<CreepStateData> creepStates;
  public ArrayList<BossStateData>  bossStates;

  public StateData findState(final String name) throws DataException {
    for (final StateData s : textStates) {
      if (s.state.equals(name)) {
        return s;
      }
    }

    for (final StateData s : creepStates) {
      if (s.state.equals(name)) {
        return s;
      }
    }

    for (final StateData s : bossStates) {
      if (s.state.equals(name)) {
        return s;
      }
    }

    throw new DataException("State " + name + " not found.");
  }

  @Override
  public void close() throws DataException {
    // No data needs clean up
  }
}
