/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.types;

public class Wallet {
  private int money;

  public Wallet(int start) {
    assert start >= 0;

    this.money = start;
  }

  public void addMoney(int value) {
    assert value >= 0;

    money += value;
  }

  public boolean takeMoney(int value) {
    assert value >= 0;

    if (money < value) {
      return false;
    }

    money -= value;
    return true;
  }

  public int getMoney() {
    return money;
  }
}
