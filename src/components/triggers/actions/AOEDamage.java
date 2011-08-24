/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package components.triggers.actions;

import main.World;
import math.Vector2;

import components.interfaces.IUnit;

public class AOEDamage implements IAction {
  final Vector2 center;
  final float   range, damage;

  public AOEDamage(final Vector2 center, final float range, final float damage) {
    this.center = center;
    this.range = range;
    this.damage = damage;
  }

  @Override
  public void execute(final World world) {
    for (final IUnit unit : world.getUnits()) {
      if (unit.getBody().getCenter().distance(center) < range) {
        unit.damage(damage);
      }
    }
  }
}
