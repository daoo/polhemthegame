package components.holdables.weapons.states;

import other.GameTime;

public interface IWeaponState {
  public void update(final GameTime time);
  public boolean isFinished();
  public float getProgress();
}
