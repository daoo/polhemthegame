/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.holdables.weapons;

public interface IMagazine {
  /**
   * Check if the magazine is empty.
   * @return true if the magazine is empty otherwise false
   */
  boolean isEmpty();

  /**
   * Take one bullet out of the magazine.
   * @return true if there was ammo, otherwise false
   */
  boolean takeOne();

  /**
   * Reload the magazine.
   */
  void reload();

  /**
   * Return the amount of bullets divided by the size of the magazine.
   * @return a float in range [0, 1]
   */
  float getFractionFilled();

}
