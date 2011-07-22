package components.holdables.weapons.states;

import other.GameTime;

public class CoolDownState implements IWeaponState {
  private final float tStart, tEnd, tLength;
  private float tCurrent;
  
  public CoolDownState(final float timeStart, final float length) {
    tCurrent = timeStart;
    tStart = timeStart;
    tEnd = timeStart + length;
    tLength = length;
  }

  @Override
  public void update(final GameTime time) {
    tCurrent = time.getElapsed();
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
