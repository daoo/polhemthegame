/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.holdables.weapons;

/**
 * Simple class for keeping track of number of projectiles that needs to be
 * spawned.
 */
public class ProjectileQueue {
  private int mCount;

  /**
   * Create a new tracker starting from zero.
   */
  public ProjectileQueue() {
    mCount = 0;
  }

  /**
   * Queue up one projectile.
   */
  public void queueUp() {
    ++mCount;
  }

  /**
   * How many projectiles are waiting to be spawned.
   *
   * @return an int greater than or equal to zero
   */
  public int getWaiting() {
    return mCount;
  }

  /**
   * Reset to zero projectiles.
   */
  public void clear() {
    mCount = 0;
  }
}
