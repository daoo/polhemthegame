/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package components.actions;

import main.World;
import entities.projectiles.Projectile;

public class SpawnProjectile implements IAction {
  private final Projectile object;

  public SpawnProjectile(final Projectile object) {
    this.object = object;
  }

  @Override
  public void execute(final World world) {
    world.add(object);
  }
}
