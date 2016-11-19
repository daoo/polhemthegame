/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.holdables.weapons;

public interface IMagazine {
  /**
   * Check if the magazine is empty.
   * @return true if the magazine is empty otherwise false
   */
  public abstract boolean isEmpty();

  /**
   * Take one bullet out of the magazine.
   * @throws OutOfAmmoException if there are no bullets
   */
  public abstract void takeOne() throws OutOfAmmoException;

  /**
   * Reload the magazine.
   */
  public abstract void reload();

  /**
   * Return the amount of bullets divided by the size of the magazine.
   * @return a float in range [0, 1]
   */
  public abstract float getFractionFilled();

}
