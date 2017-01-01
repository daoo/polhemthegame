/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.holdables.weapons;

/**
 * Simple class for managing a gun's magazine.
 */
public class FiniteMagazine implements Magazine {
  private final int mSize;
  private int mAmmo;

  /**
   * Create a new magazine with space for a certain amount of bullets.
   *
   * @param size the size of the magazine, greater than zero
   */
  public FiniteMagazine(int size) {
    assert size > 0;

    mSize = size;
    mAmmo = size;
  }

  @Override
  public boolean isEmpty() {
    return mAmmo == 0;
  }

  @Override
  public boolean takeOne() {
    if (mAmmo <= 0) {
      return false;
    }

    --mAmmo;
    return true;
  }

  @Override
  public void reload() {
    mAmmo = mSize;
  }

  @Override
  public float getFractionFilled() {
    return mAmmo / (float) mSize;
  }
}
