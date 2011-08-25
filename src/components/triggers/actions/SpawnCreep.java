/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package components.triggers.actions;

import entities.Creep;
import game.World;

public class SpawnCreep implements IAction {
  private final Creep creep;
  
  public SpawnCreep(final Creep creep) {
    assert (creep != null);
    
    this.creep = creep;
  }
  
  @Override
  public void execute(World world) {
    creep.start();
    world.add(creep);
  }
}
