/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.misc;

import game.components.holdables.weapons.Weapon;
import game.components.interfaces.ILogicComponent;
import game.misc.Wallet;
import game.types.Damage;
import game.types.GameTime;
import game.types.Message;

import java.util.ArrayList;

import math.ExMath;

public class Inventory implements ILogicComponent {
  // Money
  private final Wallet wallet;

  // Statistics
  private float damageDealt;
  private int kills;

  // Weapons
  private final ArrayList<Weapon> weapons;
  private int currentWeapon;

  public Inventory(int money) {
    wallet = new Wallet(money);

    damageDealt = 0;
    kills = 0;

    weapons = new ArrayList<>();
    currentWeapon = 0;
  }

  public void addWeapon(Weapon w) {
    assert w != null;

    weapons.add(w);
  }

  public int getKills() {
    return kills;
  }

  public Wallet getWallet() {
    return wallet;
  }

  public Weapon nextWeapon() {
    currentWeapon = ExMath.clamp(0, weapons.size() - 1, currentWeapon + 1);
    return weapons.get(currentWeapon);
  }

  public Weapon previousWeapon() {
    currentWeapon = ExMath.clamp(0, weapons.size() - 1, currentWeapon - 1);
    return weapons.get(currentWeapon);
  }

  public Weapon getWeapon(int i) {
    if (i >= 0 && i < weapons.size()) {
      currentWeapon = i;
    }

    return weapons.get(currentWeapon);
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    if (message == Message.DEALT_DAMAGE) {
      Damage dmg = (Damage) args;
      damageDealt += dmg.ammount;
    } else if (message == Message.KILLED_ENTITY) {
      ++kills;
    }
  }

  @Override
  public void update(GameTime time) {
    // Do nothing
  }

  @SuppressWarnings("boxing")
  @Override
  public String toString() {
    return String.format("Inventory - %d money, %d weapons, %d kills, %f damageDealt",
      wallet.getMoney(), weapons.size(), kills, damageDealt);
  }
}
