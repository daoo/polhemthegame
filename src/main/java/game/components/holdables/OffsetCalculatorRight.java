/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.holdables;

import game.entities.Entity;
import math.Vector2;

public class OffsetCalculatorRight implements IOffsetCalculator {
  private final Vector2 offset;
  private final Entity entity;

  public OffsetCalculatorRight(Entity entity, Vector2 handOffset) {
    this.entity = entity;
    this.offset = handOffset;
  }

  @Override
  public Vector2 getMuzzlePosition(int weaponWidth, Vector2 muzzleOffset) {
    return Vector2.add(Vector2.add(entity.body.getMin(), offset), muzzleOffset);
  }

  @Override
  public Vector2 getWeaponOffset(int weaponWidth) {
    return offset;
  }
}
