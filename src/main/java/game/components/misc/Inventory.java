/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.misc;

import java.util.ArrayList;

import game.components.LogicComponent;
import game.components.holdables.weapons.Weapon;
import game.types.Damage;
import game.types.GameTime;
import game.types.Message;
import game.types.Wallet;
import math.ExtraMath;

public class Inventory implements LogicComponent {
  // Money
  private final Wallet mWallet;

  // Statistics
  private float mDamageDealt;
  private int mKills;

  // Weapons
  private final ArrayList<Weapon> mWeapons;
  private int mCurrentWeapon;

  public Inventory(int money) {
    mWallet = new Wallet(money);

    mDamageDealt = 0;
    mKills = 0;

    mWeapons = new ArrayList<>();
    mCurrentWeapon = 0;
  }

  public void addWeapon(Weapon w) {
    assert w != null;

    mWeapons.add(w);
  }

  public int getKills() {
    return mKills;
  }

  public Wallet getWallet() {
    return mWallet;
  }

  public Weapon nextWeapon() {
    mCurrentWeapon = ExtraMath.clamp(0, mWeapons.size() - 1, mCurrentWeapon + 1);
    return mWeapons.get(mCurrentWeapon);
  }

  public Weapon previousWeapon() {
    mCurrentWeapon = ExtraMath.clamp(0, mWeapons.size() - 1, mCurrentWeapon - 1);
    return mWeapons.get(mCurrentWeapon);
  }

  public Weapon getWeapon(int i) {
    if (i >= 0 && i < mWeapons.size()) {
      mCurrentWeapon = i;
    }

    return mWeapons.get(mCurrentWeapon);
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    if (message == Message.DEALT_DAMAGE) {
      Damage dmg = (Damage) args;
      mDamageDealt += dmg.ammount;
    } else if (message == Message.KILLED_ENTITY) {
      ++mKills;
    }
  }

  @Override
  public void update(GameTime time) {
    // Do nothing
  }

  @Override
  public String toString() {
    return String
        .format("Inventory - %d money, %d weapons, %d kills, %f damageDealt", mWallet.getMoney(),
            mWeapons.size(), mKills, mDamageDealt);
  }
}
