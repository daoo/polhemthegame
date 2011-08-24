/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package components.holdables.weapons.states;

import time.GameTime;

public interface IWeaponState {
  public void update(final GameTime time);
  public boolean isFinished();
  public float getProgress();
}
