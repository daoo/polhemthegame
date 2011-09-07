/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package math;

import game.components.physics.AABB;

public class CollisionHelper {
  public static boolean SweepCollisionTest(final AABB a, final AABB b, final float dt) {
    final Vector2 vel = a.getVelocity().multiply(dt);
    final Vector2 target = a.getMin().add(vel);

    final Vector2 sweepDelta = vel.divide(a.getSize().magnitude());
    final int sweeps = (int) (target.distance(a.getMin()) / sweepDelta.magnitude());
    final Rectangle sweep = new Rectangle(a.getMin(), a.getMax());

    for (int i = 0; i < sweeps; ++i) {
      if (sweep.isIntersecting(b)) {
        return true;
      }

      sweep.addPosition(sweepDelta);
    }

    return false;
  }

  /**
   * Stops an AABB from exiting an container AABB by aligning the entity to the
   * edges of the container if it's on the outside.
   *
   * @param entity
   *          The AABB to restrict
   * @param cont
   *          The AABB to use as box
   */
  public static void BlockFromExiting(final AABB entity, final Rectangle cont) {
    final Vector2 e = entity.getMin();
    if (e.x < cont.getX1()) {
      e.x = cont.getX1();
    } else if ((e.x + entity.getWidth()) >= cont.getX2()) {
      e.x = cont.getWidth() - entity.getWidth();
    }

    if (e.y < cont.getY1()) {
      e.y = 0;
    } else if ((e.y + entity.getHeight()) >= cont.getY2()) {
      e.y = cont.getHeight() - entity.getHeight();
    }

    entity.setPosition(e);
  }
}
