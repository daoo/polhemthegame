/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.holdables.weapons;

import game.components.graphics.RSheet;
import game.components.graphics.animations.RunTo;
import game.components.graphics.animations.Tile;
import game.factories.ProjectileTemplate;
import game.time.GameTime;
import math.Vector2;

public class SingleWeapon extends Weapon {
  private boolean fireNext;

  public SingleWeapon(Vector2 muzzleOffset, float reloadTime, float cooldownTime,
                      int magazineSize, float angle, RSheet anim,
                      ProjectileTemplate factory) {
    super(muzzleOffset, reloadTime, cooldownTime, magazineSize, angle, anim, factory);

    fireNext = false;
  }

  @Override
  public void update(GameTime time) {
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
        fire(time.elapsed);
        fireNext = false;
      }
    }
  }

  @Override
  protected void fire(float elapsed) {
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
