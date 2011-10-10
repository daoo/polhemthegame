/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.misc;

import game.entities.projectiles.Projectile;
import game.world.World;
import math.time.GameTime;

public class SpawnProjectile implements IAction {
  private final Projectile object;

  public SpawnProjectile(final Projectile object) {
    assert (object != null);

    this.object = object;
  }

  @Override
  public void execute(final GameTime time, final World world) {
    world.add(object);
  }
}
