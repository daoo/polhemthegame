/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.misc;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.holdables.weapons.Weapon;
import game.components.interfaces.ILogicComponent;
import game.entities.IEntity;
import game.misc.Wallet;
import game.time.GameTime;

import java.util.LinkedList;

// TODO: Shop

public class Inventory implements ILogicComponent {
  private final Wallet wallet;

  private final LinkedList<Weapon> weapons;
  private int currentWeapon;

  public Inventory(int money) {
    wallet = new Wallet(money);
    weapons = new LinkedList<Weapon>();
    currentWeapon = 0;
  }

  public void addWeapon(Weapon w) {
    weapons.add(w);
  }

  public boolean contains(Weapon w) {
    return weapons.contains(w);
  }

  @Override
  public ComponentType getComponentType() {
    return ComponentType.INVENTORY;
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
  public void reciveMessage(ComponentMessage message, Object args) {
    // Do nothing
  }

  @Override
  public void setOwner(IEntity owner) {
    // Do nothing
  }

  public Wallet getWallet() {
    return wallet;
  }

  @Override
  public void update(GameTime time) {
    // Do nothing
  }
}
