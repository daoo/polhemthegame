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
  public static boolean sweepCollisionTest(Rectangle a, Vector2 av, Rectangle b, float dt) {
    Vector2 vel = Vector2.multiply(av, dt);
    Vector2 target = Vector2.add(a.getMin(), vel);

    float sweepLength = (float) Math
        .sqrt(ExtraMath.square(a.getWidth()) + ExtraMath.square(a.getHeight()));
    Vector2 sweepDelta = Vector2.divide(vel, sweepLength);
    int sweeps = (int) (Vector2.distance(target, a.getMin()) / sweepDelta.magnitude());
    Rectangle sweep = new Rectangle(a);

    for (int i = 0; i < sweeps; ++i) {
      if (Rectangle.intersects(sweep, b)) {
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
   * @param cont the Rectangle to use as box
   */
  public static void blockFromExiting(Rectangle entity, Rectangle cont) {
    float x = entity.getMin().x;
    if (x < cont.getX1()) {
      x = cont.getX1();
    } else if (x + entity.getWidth() >= cont.getX2()) {
      x = cont.getWidth() - entity.getWidth();
    }

    float y = entity.getMin().y;
    if (y < cont.getY1()) {
      y = cont.getY1();
    } else if (y + entity.getHeight() >= cont.getY2()) {
      y = cont.getY2() - entity.getHeight();
    }

    entity.setPosition(x, y);
  }
}
