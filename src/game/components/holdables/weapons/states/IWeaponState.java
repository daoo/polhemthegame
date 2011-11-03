/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.holdables.weapons.states;

import game.time.GameTime;

public interface IWeaponState {
  void update(GameTime time);
  boolean isFinished();
  float getProgress();
}
