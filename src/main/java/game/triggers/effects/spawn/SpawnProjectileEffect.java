/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects.spawn;

import game.course.World;
import game.entities.Entity;
import game.triggers.IEffect;
import game.types.GameTime;
import game.types.Message;
import game.types.TimePos;
import math.Vector2;

public class SpawnProjectileEffect implements IEffect {
  private final Entity projectile;
  private final Vector2 start;

  public SpawnProjectileEffect(Entity projectile, Vector2 start) {
    assert projectile != null;

    this.projectile = projectile;
    this.start = start;
  }

  @Override
  public void execute(GameTime time, World world) {
    TimePos tp = new TimePos(time.elapsedMilli, start);
    projectile.sendMessage(Message.START_AT, tp);
    world.addProjectile(projectile);
  }
}
