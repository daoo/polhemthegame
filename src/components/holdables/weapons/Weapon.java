/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package components.holdables.weapons;

import java.util.ArrayList;

import math.Vector2;
import math.time.GameTime;

import org.newdawn.slick.Graphics;


import components.graphics.RSheet;
import components.holdables.IHoldable;
import components.holdables.weapons.states.CoolDownState;
import components.holdables.weapons.states.IWeaponState;
import components.holdables.weapons.states.ReloadingState;

import entities.projectiles.ProjectileTemplate;

public abstract class Weapon implements IHoldable {
  protected final Vector2                    muzzleOffset;

  protected final float                      reloadTime, cooldownTime;
  protected final int                        magazineSize;

  protected final float                      angle;

  /**
   * How many rounds there are left in the magazine.
   */
  protected int                              rounds;

  /**
   * Spawned projectiles, for owner to pass on to world.
   */
  public final ArrayList<ProjectileTemplate> projectiles;

  protected final RSheet                     anim;
  protected final ProjectileTemplate         projTemplate;

  protected IWeaponState                     currentState;

  public Weapon(final Vector2 muzzleOffset, final float reloadTime,
                final float cooldownTime, final int magazineSize,
                final float angle, final RSheet anim,
                final ProjectileTemplate projTemplate) {
    this.muzzleOffset = muzzleOffset;
    this.reloadTime   = reloadTime;
    this.cooldownTime = cooldownTime;
    this.magazineSize = magazineSize;
    this.angle        = angle;
    this.anim         = anim;
    this.projTemplate = projTemplate;

    rounds      = magazineSize;
    projectiles = new ArrayList<ProjectileTemplate>();
  }

  @Override
  public void update(final GameTime time) {
    anim.update(time);
  }

  @Override
  public void render(final Graphics g) {
    anim.render(g);
  }

  @Override
  public float getProgress() {
    if (currentState != null) {
      return currentState.getProgress();
    }
    else {
      return (float) rounds / (float) magazineSize;
    }
  }

  public Vector2 getMuzzleOffset() {
    return muzzleOffset;
  }

  public float getAngle() {
    return angle;
  }

  protected boolean isIdle() {
    return currentState == null;
  }

  protected boolean isReadyToShoot() {
    return !isEmpty() && isIdle();
  }

  protected boolean isEmpty() {
    return (magazineSize != -1) && (rounds <= 0);
  }

  protected void fire(final float elapsed) {
    if (magazineSize != -1) {
      rounds -= 1;
    }

    projectiles.add(projTemplate);

    currentState = new CoolDownState(elapsed, cooldownTime);
  }

  protected void startReload(final GameTime time) {
    currentState = new ReloadingState(time.getElapsed(), reloadTime);
    rounds = magazineSize;
  }
}
