/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.holdables.weapons;

/**
 * Simple class for managing a gun's magazine.
 */
public class Magazine implements IMagazine {
  private final int size;
  private int ammo;

  /**
   * Create a new magazine with space for a certain amount of bullets.
   * @param size the size of the magazine, greater than zero
   */
  public Magazine(int size) {
    assert size > 0;

    this.size = size;
    ammo = size;
  }

  @Override
  public boolean isEmpty() {
    return ammo == 0;
  }

  @Override
  public void takeOne() throws OutOfAmmoException {
    if (ammo <= 0) {
      throw new OutOfAmmoException();
    } else {
      --ammo;
    }
  }

  @Override
  public void reload() {
    ammo = size;
  }

  @Override
  public float getFractionFilled() {
    return ((float) ammo / (float) size);
  }
}
