/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.misc;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.holdables.weapons.Weapon;
import game.components.interfaces.ILogicComponent;
import game.misc.Wallet;
import game.time.GameTime;

import java.util.ArrayList;

public class Inventory implements ILogicComponent {
  private final Wallet wallet;

  private final ArrayList<Weapon> weapons;
  private int currentWeapon;

  public Inventory(int money) {
    wallet = new Wallet(money);
    weapons = new ArrayList<>();
    currentWeapon = 0;
  }

  public void addWeapon(Weapon w) {
    assert w != null;

    weapons.add(w);
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

  public Wallet getWallet() {
    return wallet;
  }

  @Override
  public void update(GameTime time) {
    // Do nothing
  }
}
