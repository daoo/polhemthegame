/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.misc;

import game.components.holdables.Hand;
import game.components.holdables.weapons.Weapon;
import game.components.interfaces.ILogicComponent;
import game.components.physics.Movement;
import game.entities.IEntity;
import game.misc.Shop;
import game.types.Binds;
import game.types.GameTime;
import game.types.Message;
import main.Key;
import math.Vector2;

import org.lwjgl.input.Keyboard;

public class PlayerControl implements ILogicComponent {
  private final IEntity owner;
  private final Movement movement;
  private final Inventory inventory;
  private final Shop shop;
  private final Hand hand;

  private final Binds binds;

  private final Key keyPreviousWeapon, keyNextWeapon, keyBuy, keyHoldable;

  private final float speed;

  private int lastX, lastY;

  public PlayerControl(IEntity owner, Movement movement, Inventory inventory,
      Shop shop, Hand hand, float speed, Binds binds) {
    this.owner     = owner;
    this.movement  = movement;
    this.inventory = inventory;
    this.hand      = hand;
    this.shop      = shop;
    this.speed     = speed;
    this.binds     = binds;

    this.lastX = 0;
    this.lastY = 0;

    keyPreviousWeapon = new Key(binds.previousWeapon);
    keyNextWeapon     = new Key(binds.nextWeapon);
    keyBuy            = new Key(binds.buy);
    keyHoldable       = new Key(binds.fire);
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
        owner.sendMessage(Message.STOP_ANIMATION, null);
      } else {
        owner.sendMessage(Message.START_ANIMATION, null);
      }

      lastX = x;
      lastY = y;
    }

    movement.setVelocity(new Vector2(x * speed, y * speed));

    // Shooting
    keyHoldable.update();
    if (keyHoldable.wasPressed()) {
      owner.sendMessage(Message.START_HOLDABLE, null);
    } else if (keyHoldable.wasReleased()) {
      owner.sendMessage(Message.STOP_HOLDABLE, null);
    }

    // Weapon changing
    keyNextWeapon.update();
    keyPreviousWeapon.update();
    if (keyNextWeapon.wasPressed()) {
      hand.grab(inventory.nextWeapon());
    } else if (keyPreviousWeapon.wasPressed()) {
      hand.grab(inventory.previousWeapon());
    } else if (Keyboard.isKeyDown(binds.weapon0)) {
      hand.grab(inventory.getWeapon(0));
    } else if (Keyboard.isKeyDown(binds.weapon1)) {
      hand.grab(inventory.getWeapon(1));
    } else if (Keyboard.isKeyDown(binds.weapon2)) {
      hand.grab(inventory.getWeapon(2));
    } else if (Keyboard.isKeyDown(binds.weapon3)) {
      hand.grab(inventory.getWeapon(3));
    } else if (Keyboard.isKeyDown(binds.weapon4)) {
      hand.grab(inventory.getWeapon(4));
    }

    keyBuy.update();
    if (keyBuy.wasPressed()) {
      if (shop.canAffordNext(inventory.getWallet())) {
        Weapon weapon = shop.buyNext(inventory.getWallet());
        if (weapon != null) {
          inventory.addWeapon(weapon);
        }
      }
    }
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    // Do nothing
  }
}
