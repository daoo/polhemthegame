/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.holdables;

import org.newdawn.slick.Graphics;

import game.components.RenderComponent;
import game.components.holdables.weapons.ProjectileQueue;
import game.components.holdables.weapons.Weapon;
import game.entities.EntityImpl;
import game.factories.ProjectileFactory;
import game.triggers.effects.spawn.SpawnProjectileEffect;
import game.types.GameTime;
import game.types.Message;
import game.types.Orientation;
import game.ui.hud.infobar.Progressing;
import math.Vector2;


public class Hand implements RenderComponent, Progressing {
  private final EntityImpl mOwner;
  private final OffsetCalculator mOffsetCalc;

  /**
   * The currently held weapon, null if no weapon is being held.
   */
  private Weapon mWeapon;

  public Hand(EntityImpl owner, Orientation orientation, Vector2 handOffset) {
    mOwner = owner;

    mOffsetCalc = orientation == Orientation.RIGHT
        ? new OffsetCalculatorRight(owner, handOffset)
        : new OffsetCalculatorLeft(owner, handOffset);

    mWeapon = null;
  }

  public void startUse() {
    if (mWeapon != null) {
      mWeapon.toggleOn();
    }
  }

  public void stopUse() {
    if (mWeapon != null) {
      mWeapon.toggleOff();
    }
  }

  public void grab(Weapon newItem) {
    assert newItem != null;

    // To grab a new holdable we need to stop using the current one
    stopUse();

    mWeapon = newItem;
  }

  @Override
  public void update(GameTime time) {
    if (mWeapon != null) {
      mWeapon.update(time);

      ProjectileFactory factory = mWeapon.getProjectileFactory();
      ProjectileQueue queue = mWeapon.getQueue();

      Vector2 pos = mOffsetCalc.getMuzzlePosition(mWeapon.getWidth(), mWeapon.getMuzzleOffset());

      float x = pos.x;
      if (mWeapon.getOrientation() == Orientation.LEFT) {
        // Make sure the projectile does not hit the source entity
        x -= factory.getWidth();
      }

      // Find out if there are any projectiles that want to be spawned
      for (int i = 0; i < queue.getWaiting(); ++i) {
        EntityImpl entity = factory.makeProjectile(mOwner, x, pos.y);
        mOwner.addEffect(new SpawnProjectileEffect(entity, pos));
      }

      queue.clear();
    }
  }

  @Override
  public void render(Graphics g) {
    if (mWeapon != null) {
      g.pushTransform();

      Vector2 offset = mOffsetCalc.getWeaponOffset(mWeapon.getWidth());
      g.translate(offset.x, offset.y);

      mWeapon.render(g);

      g.popTransform();
    }
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    if (message == Message.START_HOLDABLE) {
      startUse();
    } else if (message == Message.STOP_HOLDABLE) {
      stopUse();
    }
  }

  public int getWidth() {
    return mWeapon == null ? 0 : mWeapon.getWidth();
  }

  public int getHeight() {
    return mWeapon == null ? 0 : mWeapon.getWidth();
  }

  @Override
  public float getProgress() {
    return mWeapon.getProgress();
  }

  @Override
  public String toString() {
    if (mWeapon != null) {
      return "Hand - holding " + mWeapon;
    }

    return "Hand - not holding anything";
  }
}
