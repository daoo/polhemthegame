/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package com.daoo.components.holdables.weapons;

import com.daoo.components.graphics.RSheet;
import com.daoo.components.graphics.Tile;
import com.daoo.components.graphics.animations.Continuous;
import com.daoo.components.graphics.animations.RunTo;
import com.daoo.entities.projectiles.ProjectileTemplate;
import com.daoo.math.Vector2;
import com.daoo.math.time.GameTime;

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
  protected void startReload(final GameTime time) {
    super.startReload(time);

    anim.setAnimator(new RunTo(anim.getTileCount(), Tile.ZERO));
  }
}
