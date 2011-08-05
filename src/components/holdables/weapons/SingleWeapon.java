package components.holdables.weapons;

import org.newdawn.slick.Graphics;

import basics.Vector2;

import components.graphics.RSheetOnce;

import entities.projectiles.ProjectileTemplate;

public class SingleWeapon extends Weapon {
  public SingleWeapon(final Vector2 muzzleOffset, final float reloadTime,
                      final float cooldownTime, final int magazineSize,
                      final float angle, final RSheetOnce anim,
                      final ProjectileTemplate factory) {
    super(muzzleOffset, reloadTime, cooldownTime, magazineSize, angle, anim, factory);
  }

  @Override
  public void render(final Graphics g) {
    anim.render(g);
  }

  @Override
  public void useOnce() {
    if (isReadyToShoot()) {
      nextAction = WEAPON_ACTION.FIRE_ONCE;
    }
  }

  @Override
  protected void fire(final float elapsed) {
    super.fire(elapsed);

    anim.restart();
  }

  @Override
  public void startUse() {
    useOnce();
  }

  @Override
  public void toggleUse() {
    // SingleWeapon can't be continuously used
  }

  @Override
  public void stopUse() {
    // SingleWeapon can't be continuously used
  }

  @Override
  public boolean isInUse() {
    return false;
  }
}
