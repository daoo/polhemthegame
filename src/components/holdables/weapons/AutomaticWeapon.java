package components.holdables.weapons;

import other.GameTime;
import basics.Vector2;

import components.graphics.RSheet;

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
  public boolean isInUse() {
    return inUse;
  }

  private void stopShooting() {
    inUse = false;
    anim.stop();
    anim.goToFirstFrame();
  }

  private void startShooting() {
    inUse = true;
  }
  
  @Override
  protected void startReload(GameTime time) {
    super.startReload(time);
    
    anim.stop();
  }
}
