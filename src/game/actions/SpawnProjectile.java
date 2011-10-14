/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.actions;

import game.components.ComponentMessage;
import game.entities.IEntity;
import game.world.World;
import math.time.GameTime;

public class SpawnProjectile implements IAction {
  private final IEntity projectile;

  public SpawnProjectile(final IEntity projectile) {
    assert (projectile != null);

    this.projectile = projectile;
  }

  @Override
  public void execute(final GameTime time, final World world) {
    projectile.sendMessage(ComponentMessage.START_AT, null);
    world.add(projectile);
  }
}
