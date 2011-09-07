/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.triggers.actions;

import game.entities.Unit;
import game.entities.interfaces.IObject;
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
    for (final IObject o : world.getUnits()) {
      Unit unit = (Unit) o;
      if (unit.getBody().getCenter().distance(center) < range) {
        unit.damage(damage);
      }
    }
  }
}
