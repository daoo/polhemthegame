/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package components.actions;

import main.World;
import basics.Vector2;

import components.interfaces.IUnit;

public class AOEDamage implements IAction {
  final Vector2 center;
  final float   range, damage;

  public AOEDamage(Vector2 center, float range, float damage) {
    this.center = center;
    this.range = range;
    this.damage = damage;
  }

  @Override
  public void execute(final World world) {
    for (IUnit unit : world.getUnits()) {
      if (unit.getBody().getCenter().distance(center) < range) {
        unit.damage(damage);
      }
    }
  }
}
