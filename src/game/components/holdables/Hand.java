/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.holdables;

import game.components.holdables.weapons.ProjectileQueue;
import game.components.holdables.weapons.Weapon;
import game.components.interfaces.IRenderComponent;
import game.entities.Entity;
import game.triggers.effects.spawn.SpawnProjectileEffect;
import game.types.GameTime;
import game.types.Message;
import math.Vector2;

import org.newdawn.slick.Graphics;

import ui.hud.infobar.IProgress;

public class Hand implements IRenderComponent, IProgress {
  private final Vector2 offset;

  private final Entity owner;

  /**
   * The currently held weapon, null if no weapon is being held.
   */
  private Weapon weapon;

  public Hand(Entity owner, float handOffsetX, float handOffsetY) {
    this.owner = owner;

    offset = new Vector2(handOffsetX, handOffsetY);

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
      ProjectileQueue queue = weapon.getQueue();

      // Find out if there are any projectiles that want to be spawned
      for (int i = 0; i < queue.getWaiting(); ++i) {
        // Muzzle relative to player entity
        Vector2 m = Vector2.add(offset, weapon.getMuzzleOffset());

        // Muzzle relative to the world
        Vector2 o = Vector2.add(owner.body.getMin(), m);

        // TODO: Spread
        Entity p = weapon.getProjectileFactory().makeProjectile(
            owner, o.x, o.y);

        owner.addEffect(new SpawnProjectileEffect(p, o));
      }

      queue.clear();
    }
  }

  @Override
  public void render(Graphics g) {
    if (weapon != null) {
      g.pushTransform();
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
  public float getProgress() {
    return weapon.getProgress();
  }

  @Override
  public String toString() {
    if (weapon != null) {
      return "Hand - holding " + weapon.toString();
    } else {
      return "Hand - not holding anything";
    }
  }
}
