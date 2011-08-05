package components.holdables.weapons;

import other.GameTime;
import basics.Vector2;

import components.graphics.RSheetOnce;
import components.holdables.weapons.states.ReloadingState;

import entities.projectiles.ProjectileTemplate;

public class AutomaticWeapon extends Weapon {
  /**
   * Denotes that the trigger is being pulled (for automatic mode).
   */
  private boolean inUse;

  public AutomaticWeapon(final Vector2 muzzleOffset, final float reloadTime,
                         final float cooldownTime, final int magazineSize,
                         final float angle, final RSheetOnce anim,
                         final ProjectileTemplate factory) {
    super(muzzleOffset, reloadTime, cooldownTime, magazineSize, angle, anim, factory);
  }

  @Override
  public void update(final GameTime time) {
    super.update(time);

    if (isEmpty()) {
      // Start reloading
      currentState = new ReloadingState(time.getElapsed(), reloadTime);
    } else if (nextAction == WEAPON_ACTION.FIRE_ONCE) {
      fire(time.getElapsed());
      nextAction = WEAPON_ACTION.NONE;
    } else if (isInUse() && isReadyToShoot()) {
      fire(time.getElapsed());
    }
  }

  @Override
  public void startUse() {
    startShooting();
  }

  @Override
  public void toggleUse() {
    if (isReadyToShoot()) {
      if (isInUse()) {
        stopUse();
      } else {
        startUse();
      }
    }
  }

  @Override
  public void stopUse() {
    stopShooting();
  }

  @Override
  protected void fire(final float elapsed) {
    super.fire(elapsed);

    anim.start();
  }

  @Override
  public Vector2 getMuzzleOffset() {
    return muzzleOffset;
  }

  @Override
  public float getAngle() {
    return angle;
  }

  @Override
  public boolean isInUse() {
    return inUse;
  }

  private void stopShooting() {
    inUse = false;
  }

  private void startShooting() {
    inUse = true;
    anim.start();
  }
}
