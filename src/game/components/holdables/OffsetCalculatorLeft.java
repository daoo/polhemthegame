/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.holdables;

import game.entities.Entity;
import math.Vector2;

public class OffsetCalculatorLeft implements IOffsetCalculator {
  private final Entity entity;
  private final Vector2 offset;

  public OffsetCalculatorLeft(Entity entity, Vector2 handOffset) {
    this.entity = entity;
    this.offset = new Vector2(entity.body.getWidth() - handOffset.x, handOffset.y);
  }

  @Override
  public Vector2 getMuzzlePosition(Vector2 muzzleOffset) {
    Vector2 a = entity.body.getMin();
    Vector2 b = Vector2.add(a, offset);
    Vector2 c = Vector2.add(b, -muzzleOffset.x, muzzleOffset.y);

    return c;
  }

  @Override
  public Vector2 getWeaponOffset(int weaponWidth) {
    return new Vector2(entity.body.getWidth() - offset.x - weaponWidth, offset.y);
  }
}
