/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.misc;

import org.lwjgl.input.Keyboard;

import game.components.LogicComponent;
import game.components.holdables.Hand;
import game.components.holdables.weapons.Weapon;
import game.components.physics.Movement;
import game.config.Binds;
import game.entities.Entity;
import game.misc.Shop;
import game.types.GameTime;
import game.types.Message;
import math.Vector2;
import util.Key;

public class PlayerControl implements LogicComponent {
  private final Entity mOwner;
  private final Movement mMovement;
  private final Inventory mInventory;
  private final Shop mShop;
  private final Hand mHand;

  private final Binds mBinds;

  private final Key mKeyPreviousWeapon;
  private final Key mKeyNextWeapon;
  private final Key mKeyBuy;
  private final Key mKeyHoldable;

  private final float mSpeed;

  private int mLastX;
  private int mLastY;

  public PlayerControl(
      Entity owner, Movement movement, Inventory inventory, Shop shop, Hand hand, float speed,
      Binds binds) {
    mOwner = owner;
    mMovement = movement;
    mInventory = inventory;
    mHand = hand;
    mShop = shop;
    mSpeed = speed;
    mBinds = binds;

    mLastX = 0;
    mLastY = 0;

    mKeyPreviousWeapon = new Key(binds.previousWeapon);
    mKeyNextWeapon = new Key(binds.nextWeapon);
    mKeyBuy = new Key(binds.buy);
    mKeyHoldable = new Key(binds.fire);
  }

  @Override
  public void update(GameTime time) {
    int x = 0;
    int y = 0;

    if (Keyboard.isKeyDown(mBinds.walkLeft)) {
      x -= 1;
    }
    if (Keyboard.isKeyDown(mBinds.walkRight)) {
      x += 1;
    }
    if (Keyboard.isKeyDown(mBinds.walkUp)) {
      y -= 1;
    }
    if (Keyboard.isKeyDown(mBinds.walkDown)) {
      y += 1;
    }

    if (x != mLastX || y != mLastY) {
      if (y == 0 && x == 0) {
        mOwner.sendMessage(Message.STOP_ANIMATION, null);
      } else {
        mOwner.sendMessage(Message.START_ANIMATION, null);
      }

      mLastX = x;
      mLastY = y;
    }

    mMovement.setVelocity(new Vector2(x * mSpeed, y * mSpeed));

    // Shooting
    mKeyHoldable.update();
    if (mKeyHoldable.wasPressed()) {
      mOwner.sendMessage(Message.START_HOLDABLE, null);
    } else if (mKeyHoldable.wasReleased()) {
      mOwner.sendMessage(Message.STOP_HOLDABLE, null);
    }

    // Weapon changing
    mKeyNextWeapon.update();
    mKeyPreviousWeapon.update();
    if (mKeyNextWeapon.wasPressed()) {
      mHand.grab(mInventory.nextWeapon());
    } else if (mKeyPreviousWeapon.wasPressed()) {
      mHand.grab(mInventory.previousWeapon());
    } else if (Keyboard.isKeyDown(mBinds.weapon0)) {
      mHand.grab(mInventory.getWeapon(0));
    } else if (Keyboard.isKeyDown(mBinds.weapon1)) {
      mHand.grab(mInventory.getWeapon(1));
    } else if (Keyboard.isKeyDown(mBinds.weapon2)) {
      mHand.grab(mInventory.getWeapon(2));
    } else if (Keyboard.isKeyDown(mBinds.weapon3)) {
      mHand.grab(mInventory.getWeapon(3));
    } else if (Keyboard.isKeyDown(mBinds.weapon4)) {
      mHand.grab(mInventory.getWeapon(4));
    }

    mKeyBuy.update();
    if (mKeyBuy.wasPressed()) {
      if (mShop.canAffordNext(mInventory.getWallet())) {
        Weapon weapon = mShop.buyNext(mInventory.getWallet());
        if (weapon != null) {
          mInventory.addWeapon(weapon);
        }
      }
    }
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    // Do nothing
  }
}
