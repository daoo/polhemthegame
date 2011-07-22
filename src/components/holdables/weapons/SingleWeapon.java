/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package components.holdables.weapons;


import org.newdawn.slick.Graphics;

import other.GameTime;

import basics.Vector2;

import components.graphics.RSheetOnce;
import components.holdables.weapons.states.ReloadingState;

import entities.projectiles.ProjectileTemplate;

public class SingleWeapon extends Weapon {
  public SingleWeapon(final Vector2 muzzleOffset, final float reloadTime,
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
      rounds = magazineSize;
    } else if (nextAction == WEAPON_ACTION.FIRE_ONCE) {
      fire(time.getElapsed());
      nextAction = WEAPON_ACTION.NONE;
    }
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
  protected void fire(float elapsed) {
    super.fire(elapsed);
    
    anim.restart();
  }
  
  @Override
  public void startUse() {
    useOnce();
  }

  @Override
  public void toggleUse() {}

  @Override
  public void stopUse() {}

  @Override
  public boolean isInUse() {
    return false;
  }
}
