/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.holdables.weapons;

import game.components.graphics.animations.RunTo;
import game.components.graphics.animations.Tile;
import game.components.interfaces.IAnimatedComponent;
import game.factories.ProjectileFactory;
import game.types.GameTime;
import game.types.Orientation;
import math.Vector2;

public class SingleWeapon extends Weapon {
  private boolean fireNext;

  public SingleWeapon(Vector2 muzzleOffset, float reloadTime, float cooldownTime,
                      int magazineSize, Orientation orientation,
                      IAnimatedComponent anim, ProjectileFactory factory) {
    super(muzzleOffset, reloadTime, cooldownTime, magazineSize, orientation,
        anim, factory);

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
