/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.holdables;

import game.entities.EntityImpl;
import math.Vector2;

public class OffsetCalculatorLeft implements OffsetCalculator {
  private final EntityImpl mEntity;
  private final Vector2 mNewOffset;

  public OffsetCalculatorLeft(EntityImpl entity, Vector2 handOffset) {
    mEntity = entity;

    // Since we're flipped, offset come from UPPER RIGHT corner instead of
    // UPPER LEFT.
    mNewOffset = new Vector2(entity.getBody().getSize().x - handOffset.x, handOffset.y);
  }

  @Override
  public Vector2 getMuzzlePosition(int weaponWidth, Vector2 muzzleOffset) {
    return Vector2
        .add(mEntity.getBody().getMin(), mNewOffset.x - muzzleOffset.x, mNewOffset.y + muzzleOffset.y);
  }

  @Override
  public Vector2 getWeaponOffset(int weaponWidth) {
    return new Vector2(mNewOffset.x - weaponWidth, mNewOffset.y);
  }
}
