/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.holdables;

import math.Vector2;

public interface OffsetCalculator {
  Vector2 getMuzzlePosition(int weaponWidth, Vector2 muzzleOffset);

  Vector2 getWeaponOffset(int weaponWidth);
}
