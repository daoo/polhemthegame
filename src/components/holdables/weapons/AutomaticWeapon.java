package components.holdables.weapons;

import other.GameTime;
import basics.Vector2;

import components.graphics.RSheet;
import components.graphics.Tile;
import components.graphics.animations.Continuous;
import components.graphics.animations.RunTo;

import entities.projectiles.ProjectileTemplate;

public class AutomaticWeapon extends Weapon {
  /**
   * Denotes that the trigger is being pulled (for automatic mode).
   */
  private boolean inUse;

  public AutomaticWeapon(final Vector2 muzzleOffset, final float reloadTime,
                         final float cooldownTime, final int magazineSize,
                         final float angle, final RSheet anim,
                         final ProjectileTemplate factory) {
    super(muzzleOffset, reloadTime, cooldownTime, magazineSize, angle, anim, factory);
  }

  @Override
  public void update(final GameTime time) {
    anim.update(time);
    
    if (currentState != null) {
      currentState.update(time);

      if (currentState.isFinished()) {
        currentState = null;

        if (isInUse()) {
          anim.setAnimator(new Continuous(anim.getTileCount()));
        }
      }
    }
    else {    
      if (isEmpty()) {
        startReload(time);
      } else if (isInUse() && isReadyToShoot()) {
        fire(time.getElapsed());
      }
    }
  }

  @Override
  public void toggleOn() {
    startShooting();
  }

  @Override
  public void toggleOff() {
    stopShooting();
  }

  @Override
  public boolean isInUse() {
    return inUse;
  }

  private void stopShooting() {
    inUse = false;
    anim.setAnimator(new RunTo(anim.getTileCount(), Tile.ZERO));
  }

  private void startShooting() {
    inUse = true;
  }
  
  @Override
  protected void startReload(GameTime time) {
    super.startReload(time);

    anim.setAnimator(new RunTo(anim.getTileCount(), Tile.ZERO));
  }
}
