/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.holdables.weapons;

/**
 * Simple class for managing a gun's magazine.
 */
public class Magazine {
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

  /**
   * Check if the magazine is empty.
   * @return true if the magazine is empty otherwise false
   */
  public boolean isEmpty() {
    return ammo == 0;
  }

  /**
   * Take one bullet out of the magazine.
   * @throws OutOfAmmoException if there are no bullets
   */
  public void takeOne() throws OutOfAmmoException {
    if (ammo <= 0) {
      throw new OutOfAmmoException();
    } else {
      --ammo;
    }
  }

  /**
   * Reload the magazine.
   */
  public void reload() {
    ammo = size;
  }

  /**
   * Return the amount of bullets divided by the size of the magazine.
   * @return a float in range [0, 1]
   */
  public float getFractionFilled() {
    return ((float) ammo / (float) size);
  }
}
