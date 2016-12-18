/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.holdables;

import org.newdawn.slick.Graphics;

import game.components.IRenderComponent;
import game.components.holdables.weapons.ProjectileQueue;
import game.components.holdables.weapons.Weapon;
import game.entities.Entity;
import game.factories.ProjectileFactory;
import game.triggers.effects.spawn.SpawnProjectileEffect;
import game.types.GameTime;
import game.types.Message;
import game.types.Orientation;
import game.ui.hud.infobar.IProgress;
import math.Vector2;


public class Hand implements IRenderComponent, IProgress {
  private final Entity owner;
  private final IOffsetCalculator offsetCalc;

  /**
   * The currently held weapon, null if no weapon is being held.
   */
  private Weapon weapon;

  public Hand(Entity owner, Orientation orientation, Vector2 handOffset) {
    this.owner = owner;

    offsetCalc = (orientation == Orientation.RIGHT)
        ? new OffsetCalculatorRight(owner, handOffset)
        : new OffsetCalculatorLeft(owner, handOffset);

    weapon = null;
  }

  public void startUse() {
    if (weapon != null) {
      weapon.toggleOn();
    }
  }

  public void stopUse() {
    if (weapon != null) {
      weapon.toggleOff();
    }
  }

  public void grab(Weapon newItem) {
    assert newItem != null;

    // To grab a new holdable we need to stop using the current one
    stopUse();

    weapon = newItem;
  }

  @Override
  public void update(GameTime time) {
    if (weapon != null) {
      weapon.update(time);

      ProjectileFactory factory = weapon.getProjectileFactory();
      ProjectileQueue queue = weapon.getQueue();

      Vector2 pos = offsetCalc.getMuzzlePosition(weapon.getWidth(),
          weapon.getMuzzleOffset());

      float x = pos.x;
      if (weapon.getOrientation() == Orientation.LEFT) {
        // Make sure the projectile does not hit the source entity
        x = x - factory.getWidth();
      }

      // Find out if there are any projectiles that want to be spawned
      for (int i = 0; i < queue.getWaiting(); ++i) {
        Entity entity = factory.makeProjectile(owner, x, pos.y);
        owner.addEffect(new SpawnProjectileEffect(entity, pos));
      }

      queue.clear();
    }
  }

  @Override
  public void render(Graphics g) {
    if (weapon != null) {
      g.pushTransform();

      Vector2 offset = offsetCalc.getWeaponOffset(weapon.getWidth());
      g.translate(offset.x, offset.y);

      weapon.render(g);

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

  @Override
  public int getWidth() {
    return weapon == null ? 0 : weapon.getWidth();
  }

  @Override
  public int getHeight() {
    return weapon == null ? 0 : weapon.getWidth();
  }

  @Override
  public float getProgress() {
    return weapon.getProgress();
  }

  @Override
  public String toString() {
    if (weapon != null) {
      return "Hand - holding " + weapon.toString();
    }

    return "Hand - not holding anything";
  }
}
