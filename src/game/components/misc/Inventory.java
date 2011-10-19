/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.misc;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.holdables.weapons.Weapon;
import game.components.interfaces.ILogicComponent;
import game.entities.IEntity;

import java.util.LinkedList;

import math.time.GameTime;

// TODO: Shop

public class Inventory implements ILogicComponent {
  private int money;

  private final LinkedList<Weapon> weapons;
  private int currentWeapon;

  public Inventory(int money) {
    this.money = money;
    weapons = new LinkedList<Weapon>();
    currentWeapon = 0;
  }

  public void addWeapon(Weapon w) {
    weapons.add(w);
  }

  public void addMoney(int v) {
    money += v;
  }

  public boolean contains(Weapon w) {
    return weapons.contains(w);
  }

  @Override
  public ComponentType getComponentType() {
    return ComponentType.INVENTORY;
  }

  public int getMoney() {
    return money;
  }

  public Weapon nextWeapon() {
    currentWeapon = (currentWeapon + 1) % weapons.size();
    return weapons.get(currentWeapon);
  }

  public Weapon previousWeapon() {
    currentWeapon = (currentWeapon - 1) % weapons.size();
    return weapons.get(currentWeapon);
  }

  @Override
  public void reciveMessage(final ComponentMessage message, final Object args) {
    // Do nothing
  }

  @Override
  public void setOwner(final IEntity owner) {
    // Do nothing
  }

  /***
   * Takes money from the inventory.
   * @param v The ammount of money to take
   * @return True if there is enough money in the inventory, false otherwise.
   */
  public boolean takeMoney(int v) {
    if (money < v) {
      return false;
    }

    money -= v;

    return true;
  }

  @Override
  public void update(GameTime time) {
    // Do nothing
  }
}
