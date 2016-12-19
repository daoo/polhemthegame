/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.holdables.weapons;

import org.newdawn.slick.Graphics;

import game.components.graphics.AnimatedSheet;
import game.components.holdables.IHoldable;
import game.components.holdables.weapons.machines.AutomaticMachine;
import game.components.holdables.weapons.machines.IWeaponMachine;
import game.components.holdables.weapons.machines.SingleMachine;
import game.factories.ProjectileFactory;
import game.types.GameTime;
import game.types.Message;
import game.types.Orientation;
import math.Vector2;

/**
 * Weapon that can fire and be held by a hand.
 */
public class Weapon implements IHoldable {
  private final AnimatedSheet anim;
  private final Vector2 muzzleOffset;

  private final Orientation orientation;
  private final ProjectileFactory projTemplate;

  private final IWeaponMachine machine;

  /**
   * Keep track of fired bullets that have not been spawned.
   * Note that it's possible that we never fire more than one bullet until the
   * holder have collected them and spawned them in the world.
   */
  private final ProjectileQueue queue;

  public Weapon(Vector2 muzzleOffset, WeaponMode mode, int reloadLength,
                int cooldownLength, int magazineSize, Orientation orientation,
                AnimatedSheet anim, ProjectileFactory projTemplate) {
    this.muzzleOffset = muzzleOffset;
    this.orientation  = orientation;
    this.anim         = anim;
    this.projTemplate = projTemplate;

    queue = new ProjectileQueue();

    IMagazine magazine = magazineSize == -1
      ? new InfiniteMagazine()
      : new Magazine(magazineSize);

    machine = mode == WeaponMode.AUTOMATIC
      ? new AutomaticMachine(reloadLength, cooldownLength, magazine, queue, anim)
      : new SingleMachine(reloadLength, cooldownLength, magazine, queue, anim);
  }

  public Orientation getOrientation() {
    return orientation;
  }

  public Vector2 getMuzzleOffset() {
    return muzzleOffset;
  }

  public ProjectileFactory getProjectileFactory() {
    return projTemplate;
  }

  public ProjectileQueue getQueue() {
    return queue;
  }

  @Override
  public float getProgress() {
    return machine.getProgress();
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    // Do nothing
  }

  @Override
  public void render(Graphics g) {
    anim.render(g);
  }

  @Override
  public void update(GameTime time) {
    anim.update(time);
    machine.update(time);
  }

  @Override
  public void toggleOn() {
    machine.startFiring();
  }

  @Override
  public void toggleOff() {
    machine.stopFiring();
  }

  @Override
  public int getWidth() {
    return anim.getWidth();
  }

  @Override
  public int getHeight() {
    return anim.getHeight();
  }
}
