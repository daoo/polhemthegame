/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.holdables.weapons.states;

import game.types.GameTime;

public class CoolDownState implements IWeaponState {
  private final float tStart, tEnd, tLength;
  private float tCurrent;

  public CoolDownState(float timeStart, float length) {
    tCurrent = timeStart;
    tStart = timeStart;
    tEnd = timeStart + length;
    tLength = length;
  }

  @Override
  public void update(GameTime time) {
    tCurrent = time.elapsed;
  }

  @Override
  public boolean isFinished() {
    return tCurrent >= tEnd;
  }

  /**
   * Goes from 1 (when started) to 0 (when finished).
   */
  @Override
  public float getProgress() {
    if (isFinished()) {
      return 0;
    }

    return (tCurrent - tStart) / tLength;
  }
}
