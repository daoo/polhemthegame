package components.holdables.weapons;

import org.newdawn.slick.Graphics;

import basics.Vector2;

import components.graphics.RSheet;

import entities.projectiles.ProjectileTemplate;

public class SingleWeapon extends Weapon {
  public SingleWeapon(final Vector2 muzzleOffset, final float reloadTime,
                      final float cooldownTime, final int magazineSize,
                      final float angle, final RSheet anim,
                      final ProjectileTemplate factory) {
    super(muzzleOffset, reloadTime, cooldownTime, magazineSize, angle, anim, factory);
  }

  @Override
  public void render(final Graphics g) {
    anim.render(g);
  }

  @Override
  protected void fire(final float elapsed) {
    super.fire(elapsed);

    anim.goToFirstFrame();
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
