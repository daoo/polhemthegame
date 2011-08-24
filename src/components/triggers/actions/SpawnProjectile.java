/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package components.triggers.actions;

import entities.projectiles.Projectile;
import game.World;

public class SpawnProjectile implements IAction {
  private final Projectile object;

  public SpawnProjectile(final Projectile object) {
    assert (object != null);

    this.object = object;
  }

  @Override
  public void execute(final World world) {
    world.add(object);
  }
}
