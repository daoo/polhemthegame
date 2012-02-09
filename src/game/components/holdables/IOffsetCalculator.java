/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.holdables;

import math.Vector2;

public interface IOffsetCalculator {
  Vector2 getMuzzlePosition(Vector2 muzzleOffset);
  Vector2 getWeaponOffset(int weaponWidth);
}
