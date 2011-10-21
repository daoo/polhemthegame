/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.holdables.weapons;

import game.components.graphics.RSheet;
import game.components.graphics.Tile;
import game.components.graphics.animations.Continuous;
import game.components.graphics.animations.RunTo;
import game.entities.ProjectileTemplate;
import math.Vector2;
import math.time.GameTime;

public class AutomaticWeapon extends Weapon {
  /**
   * Denotes that the trigger is being pulled (for automatic mode).
   */
  private boolean inUse;

  public AutomaticWeapon(Vector2 muzzleOffset, float reloadTime, float cooldownTime,
                         int magazineSize, float angle, RSheet anim,
                         ProjectileTemplate factory) {
    super(muzzleOffset, reloadTime, cooldownTime, magazineSize, angle, anim, factory);
  }

  @Override
  public void update(GameTime time) {
    super.update(time);

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
        fire(time.elapsed);
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
    if (!anim.getAnimator().isFinished()) {
      anim.setAnimator(new RunTo(anim.getTileCount(), Tile.ZERO));
    }
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
