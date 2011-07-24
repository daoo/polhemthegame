/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package components.basic;

import java.util.ArrayList;

import other.NotEnoughCashishException;

import components.holdables.weapons.Weapon;
import components.interfaces.IComp;

public class Inventory implements IComp {
  protected int                     money;
  protected final ArrayList<Weapon> weapons;
  protected int                     currentWeapon;

  public Inventory(final int money) {
    this.money = money;
    weapons = new ArrayList<Weapon>();
    currentWeapon = 0;
  }

  public void takeMoney(final int v) throws NotEnoughCashishException {
    if (money < v) {
      throw new NotEnoughCashishException();
    }

    money -= v;
  }

  public void addMoney(final int v) {
    money += v;
  }

  public void add(final Weapon w) {
    weapons.add(w);
  }

  public boolean contains(final Weapon w) {
    return weapons.contains(w);
  }

  public Weapon previousWeapon() {
    currentWeapon = (currentWeapon - 1) % weapons.size();
    return weapons.get(currentWeapon);
  }

  public Weapon nextWeapon() {
    currentWeapon = (currentWeapon + 1) % weapons.size();
    return weapons.get(currentWeapon);
  }
}
