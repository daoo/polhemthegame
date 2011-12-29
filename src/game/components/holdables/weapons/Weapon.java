/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.holdables.weapons;

import game.components.ComponentType;
import game.components.Message;
import game.components.graphics.RSheet;
import game.components.holdables.IHoldable;
import game.components.holdables.weapons.states.CoolDownState;
import game.components.holdables.weapons.states.IWeaponState;
import game.components.holdables.weapons.states.ReloadingState;
import game.factories.ProjectileFactory;
import game.pods.GameTime;

import java.util.LinkedList;

import math.Vector2;

import org.newdawn.slick.Graphics;

public abstract class Weapon implements IHoldable {
  protected IWeaponState currentState;
  /**
   * How many rounds there are left in the magazine.
   */
  protected int rounds;

  protected final RSheet anim;
  protected final float reloadTime, cooldownTime;
  protected final int magazineSize;
  protected final Vector2 muzzleOffset;

  protected final float angle;
  protected final ProjectileFactory projTemplate;
  /**
   * Spawned projectiles, for owner to pass on to world.
   */
  public final LinkedList<ProjectileFactory> projectiles;

  public Weapon(Vector2 muzzleOffset, float reloadTime, float cooldownTime,
                int magazineSize, float angle, RSheet anim,
                ProjectileFactory projTemplate) {
    this.muzzleOffset = muzzleOffset;
    this.reloadTime   = reloadTime;
    this.cooldownTime = cooldownTime;
    this.magazineSize = magazineSize;
    this.angle        = angle;
    this.anim         = anim;
    this.projTemplate = projTemplate;

    rounds      = magazineSize;
    projectiles = new LinkedList<>();
  }

  public float getAngle() {
    return angle;
  }

  @Override
  public ComponentType getComponentType() {
    return ComponentType.WEAPON;
  }

  public Vector2 getMuzzleOffset() {
    return muzzleOffset;
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

  @Override
  public void reciveMessage(Message message, Object args) {
    if (message == Message.KILL) {
      toggleOff();
    }
  }

  @Override
  public void render(Graphics g) {
    anim.render(g);
  }

  @Override
  public void update(GameTime time) {
    anim.update(time);
  }

  protected void fire(float elapsed) {
    if (magazineSize != -1) {
      rounds -= 1;
    }

    projectiles.add(projTemplate);

    currentState = new CoolDownState(elapsed, cooldownTime);
  }

  protected boolean isEmpty() {
    return (magazineSize != -1) && (rounds <= 0);
  }

  protected boolean isIdle() {
    return currentState == null;
  }

  protected boolean isReadyToShoot() {
    return !isEmpty() && isIdle();
  }

  protected void startReload(GameTime time) {
    currentState = new ReloadingState(time.elapsed, reloadTime);
    rounds = magazineSize;
  }
}
