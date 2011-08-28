/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package com.daoo.components.holdables.weapons.states;

import com.daoo.math.time.GameTime;

public interface IWeaponState {
  public void update(final GameTime time);
  public boolean isFinished();
  public float getProgress();
}
