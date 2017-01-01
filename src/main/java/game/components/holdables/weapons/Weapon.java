/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.holdables.weapons;

import org.newdawn.slick.Graphics;

import game.components.graphics.AnimatedSheet;
import game.components.holdables.Holdable;
import game.components.holdables.weapons.machines.AutomaticMachine;
import game.components.holdables.weapons.machines.WeaponMachine;
import game.components.holdables.weapons.machines.SingleMachine;
import game.factories.ProjectileFactory;
import game.types.GameTime;
import game.types.Message;
import game.types.Orientation;
import math.Vector2;

/**
 * Weapon that can fire and be held by a hand.
 */
public class Weapon implements Holdable {
  private final AnimatedSheet mAnim;
  private final Vector2 mMuzzleOffset;

  private final Orientation mOrientation;
  private final ProjectileFactory mProjTemplate;

  private final WeaponMachine mMachine;

  /**
   * Keep track of fired bullets that have not been spawned.
   * Note that it's possible that we never fire more than one bullet until the
   * holder have collected them and spawned them in the world.
   */
  private final ProjectileQueue mQueue;

  public Weapon(
      Vector2 muzzleOffset, WeaponMode mode, int reloadLength, int cooldownLength, int magazineSize,
      Orientation orientation, AnimatedSheet anim, ProjectileFactory projTemplate) {
    mMuzzleOffset = muzzleOffset;
    mOrientation = orientation;
    mAnim = anim;
    mProjTemplate = projTemplate;

    mQueue = new ProjectileQueue();

    Magazine magazine = magazineSize == -1 ? new InfiniteMagazine() : new FiniteMagazine(magazineSize);

    mMachine = mode == WeaponMode.AUTOMATIC
        ? new AutomaticMachine(reloadLength, cooldownLength, magazine, mQueue, anim)
        : new SingleMachine(reloadLength, cooldownLength, magazine, mQueue, anim);
  }

  public Orientation getOrientation() {
    return mOrientation;
  }

  public Vector2 getMuzzleOffset() {
    return mMuzzleOffset;
  }

  public ProjectileFactory getProjectileFactory() {
    return mProjTemplate;
  }

  public ProjectileQueue getQueue() {
    return mQueue;
  }

  @Override
  public float getProgress() {
    return mMachine.getProgress();
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    // Do nothing
  }

  @Override
  public void render(Graphics g) {
    mAnim.render(g);
  }

  @Override
  public void update(GameTime time) {
    mAnim.update(time);
    mMachine.update(time);
  }

  @Override
  public void toggleOn() {
    mMachine.startFiring();
  }

  @Override
  public void toggleOff() {
    mMachine.stopFiring();
  }

  public int getWidth() {
    return mAnim.getWidth();
  }

  public int getHeight() {
    return mAnim.getHeight();
  }
}
