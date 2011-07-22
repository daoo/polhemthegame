package components.holdables.weapons.states;

import other.GameTime;

public class ReloadingState implements IWeaponState {
  private final float tEnd, tLength;
  private float tCurrent;
  
  public ReloadingState(final float timeStart, final float length) {
    tCurrent = timeStart;
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

  @Override
  public float getProgress() {
    if (isFinished()) {
      return 1;
    }
    
    return (tEnd - tCurrent) / tLength;
  }

}
