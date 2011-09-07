/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.triggers.actions;

import game.entities.Creep;
import game.world.World;
import math.time.GameTime;

public class SpawnCreep implements IAction {
  private final Creep creep;

  public SpawnCreep(final Creep creep) {
    assert (creep != null);

    this.creep = creep;
  }

  @Override
  public void execute(final GameTime time, final World world) {
    creep.start();
    world.add(creep);
  }
}
