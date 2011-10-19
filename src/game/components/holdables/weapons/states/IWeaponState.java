/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.holdables.weapons.states;

import math.time.GameTime;

public interface IWeaponState {
  void update(GameTime time);
  boolean isFinished();
  float getProgress();
}
