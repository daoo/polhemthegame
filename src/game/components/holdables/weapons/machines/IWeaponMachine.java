/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.holdables.weapons.machines;

import game.types.GameTime;

/**
 * State machine for a weapon.
 */
public interface IWeaponMachine {
  /**
   * Start firing.
   * Really starts next time we're idle and not out of bullets.
   */
  void startFiring();

  /**
   * Stop firing.
   * Should happen immediately, that is no more bullets will be fired until
   * startFiring() is called again.
   */
  void stopFiring();

  /**
   * Update the state.
   * @param time the game time
   */
  void update(GameTime time);

  /**
   * Return the progress as a fraction.
   * @return a float in the range [0, 1]
   */
  float getProgress();
}
