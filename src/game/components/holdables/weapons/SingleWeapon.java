/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.holdables.weapons;

import game.components.graphics.RSheet;
import game.components.graphics.Tile;
import game.components.graphics.animations.RunTo;
import game.entities.ProjectileTemplate;
import math.Vector2;
import math.time.GameTime;

public class SingleWeapon extends Weapon {
  private boolean fireNext;

  public SingleWeapon(final Vector2 muzzleOffset, final float reloadTime,
                      final float cooldownTime, final int magazineSize,
                      final float angle, final RSheet anim,
                      final ProjectileTemplate factory) {
    super(muzzleOffset, reloadTime, cooldownTime, magazineSize, angle, anim, factory);

    fireNext = false;
  }

  @Override
  public void update(final GameTime time) {
    super.update(time);

    if (currentState != null) {
      currentState.update(time);

      if (currentState.isFinished()) {
        currentState = null;
      }
    }
    else {
      if (isEmpty()) {
        startReload(time);
      } else if (fireNext) {
        fire(time.getElapsed());
        fireNext = false;
      }
    }
  }

  @Override
  protected void fire(final float elapsed) {
    super.fire(elapsed);

    anim.goToFirstFrame();
    anim.setAnimator(new RunTo(anim.getTileCount(), Tile.ZERO));
  }

  @Override
  public void toggleOn() {
    if (isReadyToShoot()) {
      fireNext = true;
    }
  }

  @Override
  public void toggleOff() {
    // SingleWeapon can't be continuously used
  }

  @Override
  public boolean isInUse() {
    return false;
  }
}
