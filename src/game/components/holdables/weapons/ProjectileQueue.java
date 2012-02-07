package game.components.holdables.weapons;

/**
 * Simple class for keeping track of number of projectiles that needs to be
 * spawned.
 */
public class ProjectileQueue {
  private int count;

  /**
   * Create a new tracker starting from zero.
   */
  public ProjectileQueue() {
    count = 0;
  }

  /**
   * Queue up one projectile.
   */
  public void queueUp() {
    ++count;
  }

  /**
   * How many projectiles are waiting to be spawned.
   * @return an int greater than or equal to zero
   */
  public int getWaiting() {
    return count;
  }

  /**
   * Reset to zero projectiles.
   */
  public void clear() {
    count = 0;
  }
}
