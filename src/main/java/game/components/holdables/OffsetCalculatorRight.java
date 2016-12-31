/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.holdables;

import game.entities.Entity;
import math.Vector2;

public class OffsetCalculatorRight implements IOffsetCalculator {
  private final Vector2 mOffset;
  private final Entity mEntity;

  public OffsetCalculatorRight(Entity entity, Vector2 handOffset) {
    mEntity = entity;
    mOffset = handOffset;
  }

  @Override
  public Vector2 getMuzzlePosition(int weaponWidth, Vector2 muzzleOffset) {
    return Vector2.add(Vector2.add(mEntity.getBody().getMin(), mOffset), muzzleOffset);
  }

  @Override
  public Vector2 getWeaponOffset(int weaponWidth) {
    return mOffset;
  }
}
