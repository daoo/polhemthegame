/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.triggers.actions;

import game.components.ComponentMessage;
import game.entities.interfaces.IEntity;
import game.world.World;
import math.Vector2;
import math.time.GameTime;

public class AOEDamage implements IAction {
  final Vector2 center;
  final float   range, damage;

  public AOEDamage(final Vector2 center, final float range, final float damage) {
    this.center = center;
    this.range = range;
    this.damage = damage;
  }

  @Override
  public void execute(final GameTime time, final World world) {
    for (final IEntity e : world.getUnits()) {
      if (e.getBody().getCenter().distance(center) < range) {
        e.sendMessage(ComponentMessage.DAMAGE, damage);
      }
    }
  }
}
