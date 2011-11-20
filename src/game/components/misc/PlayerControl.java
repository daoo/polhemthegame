/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.misc;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.holdables.Hand;
import game.components.holdables.weapons.Weapon;
import game.components.interfaces.ILogicComponent;
import game.components.physics.Movement;
import game.entities.IEntity;
import game.misc.Shop;
import game.pods.Binds;
import game.time.GameTime;
import math.Vector2;

import org.lwjgl.input.Keyboard;

public class PlayerControl implements ILogicComponent {
  private final IEntity owner;
  private final Movement movement;
  private final Inventory inventory;
  private final Shop shop;
  private final Hand hand;

  private final Binds binds;

  private final float speed;

  private int lastX, lastY;
  private boolean holdableOn, weaponChanged, buying;

  public PlayerControl(IEntity owner, Movement movement, Inventory inventory, Shop shop, Hand hand, float speed) {
    this.owner     = owner;
    this.movement  = movement;
    this.inventory = inventory;
    this.hand      = hand;
    this.shop      = shop;

    this.speed = speed;

    binds = new Binds();

    this.lastX      = 0;
    this.lastY      = 0;
    this.holdableOn = false;
  }

  @Override
  public void update(GameTime time) {
    int x = 0;
    int y = 0;

    if (Keyboard.isKeyDown(binds.walkLeft))
      x += -1;
    if (Keyboard.isKeyDown(binds.walkRight))
      x += 1;
    if (Keyboard.isKeyDown(binds.walkUp))
      y += -1;
    if (Keyboard.isKeyDown(binds.walkDown))
      y += 1;

    if ((x != lastX) || (y != lastY)) {
      if ((y == 0) && (x == 0)) {
        owner.sendMessage(ComponentMessage.STOP_ANIMATION, null);
      } else {
        owner.sendMessage(ComponentMessage.START_ANIMATION, null);
      }

      lastX = x;
      lastY = y;
    }

    movement.setVelocity(new Vector2(x * speed, y * speed));

    // Shooting
    if (Keyboard.isKeyDown(binds.fire)) {
      if (!holdableOn) {
        owner.sendMessage(ComponentMessage.START_HOLDABLE, null);
        holdableOn = true;
      }
    } else {
      owner.sendMessage(ComponentMessage.STOP_HOLDABLE, null);
      holdableOn = false;
    }

    // Weapon changing
    if (Keyboard.isKeyDown(binds.nextWeapon)) {
      if (!weaponChanged) {
        hand.grab(inventory.nextWeapon());
        weaponChanged = true;
      }
    } else {
      weaponChanged = false;
    }

    if (Keyboard.isKeyDown(binds.buy)) {
      if (!buying) {
        if (shop.canAffordNext(inventory.getWallet())) {
          Weapon weapon = shop.buyNext(inventory.getWallet());
          if (weapon != null) {
            inventory.addWeapon(weapon);
          }
        }

        buying = true;
      }
    } else {
      buying = false;
    }
  }

  @Override
  public void reciveMessage(ComponentMessage message, Object args) {
    // Do nothing
  }

  @Override
  public ComponentType getComponentType() {
    return ComponentType.CONTROL;
  }
}
