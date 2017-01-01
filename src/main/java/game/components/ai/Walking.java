/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.ai;

import game.components.holdables.Hand;
import game.components.physics.Movement;
import game.misc.Locator;
import game.types.GameTime;
import math.Aabb;
import math.Circle;
import math.ExtraMath;
import math.Vector2;
import util.Random;

public class Walking implements IBossState {
  public static final float EPSILON = 0.0001f;
  public static final int MIN_WALK = 100;
  public static final int MIN_WALK_SQUARED = ExtraMath.square(MIN_WALK);

  private final Aabb mBody;
  private final float mSpeed;
  private final Movement mMovement;
  private final Aabb mBoundary;

  private Vector2 mTarget;
  private int mTargetsLeft;

  /**
   * Create a new walk state for boss AI.
   *
   * @param body the boss body
   * @param hand the boss hand
   * @param movement the boss movement
   * @param speed the speed of the boss
   * @param boundary the rectangle which the boss should move within
   * @param targets the number of targets (positions the boss should walk to),
   * greater than zero
   * @param target the first target the boss should reach
   */
  public Walking(
      Aabb body, Hand hand, Movement movement, float speed, Aabb boundary, int targets,
      Vector2 target) {
    assert targets > 0;
    assert target != null;

    mBody = body;
    mSpeed = speed;
    mMovement = movement;
    mTargetsLeft = targets;
    mBoundary = boundary;
    mTarget = target;
  }

  /**
   * Create a new walk state for boss AI.
   * Instead of specifying the initial target a random target will be chosen
   * instead.
   *
   * @param body the body box
   * @param hand the boss hand
   * @param movement the boss movement
   * @param speed the speed of the boss
   * @param boundary the box which the boss should move within
   * @param targets the number of targets (positions the boss should walk to)
   */
  public Walking(
      Aabb body, Hand hand, Movement movement, float speed, Aabb boundary, int targets) {
    this(body, hand, movement, speed, boundary, targets,
        newRandomTarget(Locator.getRandom(), boundary,
            new Circle(body.getCenter(), MIN_WALK_SQUARED)));
  }

  @Override
  public void start(GameTime time) {
    headFor(mTarget);
  }

  @Override
  public void update(GameTime time) {
    if (mTargetsLeft > 0) {
      Vector2 position = mBody.getMin();
      Vector2 delta = Vector2.subtract(position, mTarget);
      if (Vector2.dot(delta, mMovement.getVelocity()) >= 0) {
        --mTargetsLeft;
        // Target passed
        if (mTargetsLeft > 0) {
          Circle circle = new Circle(position, MIN_WALK_SQUARED);
          mTarget = newRandomTarget(Locator.getRandom(), mBoundary, circle);
          headFor(mTarget);
        }
      }
    }
  }

  public Vector2 getTarget() {
    return mTarget;
  }

  @Override
  public boolean isFinished() {
    return mTargetsLeft == 0;
  }

  @Override
  public BossState getState() {
    return BossState.WALKING;
  }

  private void headFor(Vector2 target) {
    assert mBoundary.contains(target);

    Vector2 delta = Vector2.subtract(target, mBody.getMin());
    Vector2 direction = delta.normalize();
    Vector2 velocity = Vector2.multiply(direction, mSpeed);
    mMovement.setVelocity(velocity);
  }

  /**
   * Generate a random position that lies within a box but outside of a circle.
   *
   * @param rnd the random generator to use
   * @param boundary the including box
   * @param circle the excluding circle
   * @return a vector specifying a random position
   */
  public static Vector2 newRandomTarget(Random rnd, Aabb boundary, Circle circle) {
    /* The problem:
     * We want to generate a random point within a rectangle (movementRect) but
     * that lies outside of a circle (defined by body position and
     * MIN_WALK_SQUARED).
     *
     * This can be solved easily if we first make the following assumption:
     * For all values of x inside the rectangle, there is at least one value of
     * y that lies outside of the circle.
     *
     * Based on this assumption we can generate a random point easily.
     */

    // First, generate a random x value.
    float targetX = rnd.nextFloat(boundary.getMin().x, boundary.getMax().y);

    /* Now use the circle's equation:
     * (x - a)^2 + (y - b)^2 = r^2
     * => y = b + sqrt(r^2 - (x - a)^2)
     *
     * If r^2 is less than (x - a)^2, the root will become imaginary, thus all
     * y values lies outside of the circle for this x.
     */

    float tmp = ExtraMath.square(targetX - circle.center.x);
    if (circle.radius < tmp) {
      // Circle is not intersecting our x here
      return new Vector2(targetX, rnd.nextFloat(boundary.getMin().y, boundary.getMax().y));
    }

    // Here we have to take the circle into account
    float yRoot = (float) Math.sqrt(circle.radius - tmp);

    // Top and bottom of the circle
    float top = circle.center.y - yRoot - EPSILON;
    float bottom = circle.center.y + yRoot + EPSILON;

    // Note that portions of the circle could lie outside of the rectangle.
    if (bottom < boundary.getMin().y || top > boundary.getMax().y) {
      // Entire circle is outside of the rectangle
      return new Vector2(targetX, rnd.nextFloat(boundary.getMin().y, boundary.getMax().y));
    } else if (top < boundary.getMin().y) {
      // Top of circle is above the rectangle
      return new Vector2(targetX, rnd.nextFloat(bottom, boundary.getMax().y));
    } else if (bottom > boundary.getMax().y) {
      // Bottom of circle is below the rectangle
      return new Vector2(targetX, rnd.nextFloat(boundary.getMin().y, top));
    } else {
      // The part of the circle we're interested in is within the rectangle
      // We now have two intervals, note that the size of the two intervals
      // differ, we have to be careful so we get an uniform distribution.

      float topSize = top - boundary.getMin().y;
      float bottomSize = boundary.getMax().y - bottom;
      float totalSize = topSize + bottomSize;

      if (rnd.nextFloat(totalSize) < topSize) {
        // Upper
        return new Vector2(targetX, rnd.nextFloat(boundary.getMin().y, top));
      } else {
        // Lower
        return new Vector2(targetX, rnd.nextFloat(bottom, boundary.getMax().y));
      }
    }
  }
}
