/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package com.daoo.components.triggers.actions;

import com.daoo.entities.projectiles.Projectile;
import com.daoo.game.World;
import com.daoo.math.time.GameTime;

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
