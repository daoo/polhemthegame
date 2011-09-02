/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package components.triggers.actions;

import math.time.GameTime;
import entities.projectiles.Projectile;
import game.World;

public class SpawnProjectile implements IAction {
  private final Projectile object;

  public SpawnProjectile(final Projectile object) {
    assert (object != null);

    this.object = object;
  }

  @Override
  public void execute(final GameTime time, final World world) {
    world.addProjectile(object);
  }
}
