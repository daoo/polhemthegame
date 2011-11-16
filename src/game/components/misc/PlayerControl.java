/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.misc;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.holdables.Hand;
import game.components.interfaces.ILogicComponent;
import game.components.physics.Movement;
import game.entities.IEntity;
import game.misc.Shop;
import game.pods.Binds;
import game.time.GameTime;

import math.Vector2;

import org.lwjgl.input.Keyboard;

public class PlayerControl implements ILogicComponent {
  private IEntity owner;
  private Movement movement;
  private Inventory inventory;
  private Shop shop;
  private Hand hand;

  private final float speed;

  private int lastX, lastY;
  private boolean holdableOn, weaponChanged, buying;

  private final Binds binds;

  public PlayerControl(float speed, Shop shop) {
    this.speed = speed;
    this.shop  = shop;

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
          shop.buyNext(inventory.getWallet());
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

  @Override
  public void setOwner(IEntity owner) {
    this.owner     = owner;
    this.movement  = (Movement) owner.getComponent(ComponentType.MOVEMENT);
    this.inventory = (Inventory) owner.getComponent(ComponentType.INVENTORY);
    this.hand      = (Hand) owner.getComponent(ComponentType.HAND);
  }
}
