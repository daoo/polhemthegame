/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects.spawn;

import game.course.World;
import game.entities.EntityImpl;
import game.triggers.Effect;
import game.types.GameTime;
import game.types.Message;
import game.types.TimePos;
import math.Vector2;

public class SpawnProjectileEffect implements Effect {
  private final EntityImpl mProjectile;
  private final Vector2 mStart;

  public SpawnProjectileEffect(EntityImpl projectile, Vector2 start) {
    assert projectile != null;

    mProjectile = projectile;
    mStart = start;
  }

  @Override
  public void execute(GameTime time, World world) {
    TimePos tp = new TimePos(time.elapsedMilli, mStart);
    mProjectile.sendMessage(Message.START_AT, tp);
    world.addProjectile(mProjectile);
  }
}
