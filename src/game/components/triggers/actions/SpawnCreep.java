/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.triggers.actions;

import math.time.GameTime;
import game.World;
import game.entities.Creep;

public class SpawnCreep implements IAction {
  private final Creep creep;
  
  public SpawnCreep(final Creep creep) {
    assert (creep != null);
    
    this.creep = creep;
  }
  
  @Override
  public void execute(final GameTime time, final World world) {
    creep.start();
    world.addCreep(creep);
  }
}
