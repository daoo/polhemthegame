/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.types;

public class Wallet {
  private int mMoney;

  public Wallet(int start) {
    assert start >= 0;
    mMoney = start;
  }

  public void addMoney(int value) {
    assert value >= 0;

    mMoney += value;
  }

  public boolean takeMoney(int value) {
    assert value >= 0;

    if (mMoney < value) {
      return false;
    }

    mMoney -= value;
    return true;
  }

  public int getMoney() {
    return mMoney;
  }
}
