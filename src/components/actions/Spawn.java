/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package components.actions;

import main.World;
import entities.projectiles.Projectile;

public class Spawn implements IAction {
  private final Projectile object;

  public Spawn(final Projectile object) {
    this.object = object;
  }

  @Override
  public void execute(final World world) {
    world.add(object);
  }
}
