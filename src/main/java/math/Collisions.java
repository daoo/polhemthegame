/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package math;

public final class Collisions {
  private Collisions() {
  }

  /**
   * Approximate method for determining if one moving and one static rectangle
   * will intersect within the given time.
   *
   * @param a the moving rectangle
   * @param av the velocity of the moving rectangle
   * @param b the static rectangle
   * @param dt the time delta
   * @return true or false based on the test result
   */
  public static boolean sweepCollisionTest(Aabb a, Vector2 av, Aabb b, float dt) {
    Vector2 vel = Vector2.multiply(av, dt);
    Vector2 target = Vector2.add(a.getMin(), vel);

    float sweepLength = a.getSize().magnitude();
    Vector2 sweepDelta = Vector2.divide(vel, sweepLength);
    int sweeps = (int) (Vector2.distance(target, a.getMin()) / sweepDelta.magnitude());
    Aabb sweep = new Aabb(a);

    for (int i = 0; i < sweeps; ++i) {
      if (sweep.intersects(b)) {
        return true;
      }

      sweep.addPosition(sweepDelta);
    }

    return false;
  }

  /**
   * Stops an Rectangle from exiting an container Rectangle by aligning the
   * entity to the edges of the container if it's on the outside.
   *
   * @param entity the Rectangle to restrict
   * @param boundary the Rectangle to use as box
   */
  public static void blockFromExiting(Aabb entity, Aabb boundary) {
    Vector2 min = boundary.getMin();
    Vector2 max = Vector2.subtract(boundary.getMax(), entity.getSize());
    Vector2 position = entity.getMin();
    entity.setPosition(new Vector2(
        ExtraMath.clamp(min.x, max.x, position.x),
        ExtraMath.clamp(min.y, max.y, position.y)));
  }
}
