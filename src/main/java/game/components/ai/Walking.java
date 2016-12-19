/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.ai;

import game.components.holdables.Hand;
import game.components.physics.Movement;
import game.misc.Locator;
import game.types.GameTime;
import math.ExtraMath;
import math.Rectangle;
import math.Vector2;
import util.Random;

public class Walking implements IBossState {
  public static final int MIN_WALK         = 100;
  public static final int MIN_WALK_SQUARED = ExtraMath.square(MIN_WALK);

  private final Rectangle body;
  private final float speed;
  private final Movement movement;
  private final Rectangle movementRect;

  private Vector2 currentTarget;
  private int targetsLeft;

  /**
   * Create a new walk state for boss AI.
   * @param body the boss body
   * @param hand the boss hand
   * @param movement the boss movement
   * @param speed the speed of the boss
   * @param movementRect the rectangle which the boss should move within
   * @param targets the number of targets (positions the boss should walk to),
   *                greater than zero
   * @param initialTarget the first target the boss should reach
   */
  public Walking(Rectangle body, Hand hand, Movement movement, float speed,
      Rectangle movementRect, int targets, Vector2 initialTarget) {
    assert targets > 0;
    assert initialTarget != null;

    this.body         = body;
    this.speed        = speed;
    this.movement     = movement;
    this.targetsLeft      = targets;
    this.movementRect = movementRect;
    this.currentTarget       = initialTarget;
  }

  /**
   * Create a new walk state for boss AI.
   * Instead of specifying the initial target a random target will be chosen
   * instead.
   * @param entity the boss entity
   * @param hand the boss hand
   * @param movement the boss movement
   * @param speed the speed of the boss
   * @param movementRect the rectangle which the boss should move within
   * @param targets the number of targets (positions the boss should walk to)
   */
  public Walking(Rectangle body, Hand hand, Movement movement, float speed,
      Rectangle movementRect, int targets) {
    this(body, hand, movement, speed, movementRect, targets,
        newRandomTarget(Locator.getRandom(),
            (int) movementRect.getX1(), (int) movementRect.getY1(),
            (int) movementRect.getX2(), (int) movementRect.getY2(),
            (int) body.getCenter().x, (int) body.getCenter().y,
            MIN_WALK_SQUARED));
  }

  @Override
  public void start(GameTime time) {
    headFor(currentTarget);
  }

  @Override
  public void update(GameTime time) {
    if (targetsLeft > 0) {
      Vector2 a = Vector2.subtract(body.getMin(), currentTarget);
      if (Vector2.dot(a, movement.getVelocity()) >= 0) {
        --targetsLeft;
        // Target passed
        if (targetsLeft > 0) {
          currentTarget = newRandomTarget(Locator.getRandom(),
              (int) movementRect.getX1(), (int) movementRect.getY1(),
              (int) movementRect.getX2(), (int) movementRect.getY2(),
              (int) body.getX1(), (int) body.getY1(),
              MIN_WALK_SQUARED);
          headFor(currentTarget);
        }
      }
    }
  }

  public Vector2 getTarget() {
    return currentTarget;
  }

  @Override
  public boolean isFinished() {
    return targetsLeft == 0;
  }

  @Override
  public BossState getState() {
    return BossState.WALKING;
  }

  private void headFor(Vector2 target) {
    assert Rectangle.contains(movementRect, target);

    Vector2 delta     = Vector2.subtract(target, body.getMin());
    Vector2 direction = delta.normalize();
    Vector2 velocity  = Vector2.multiply(direction, speed);
    movement.setVelocity(velocity);
  }

  /**
   * Generate a random position that lies within a rectangle but outside of a
   * circle.
   * @param rnd the random generator to use
   * @param rx1 top left x of the rectangle
   * @param ry1 top left y of the rectangle
   * @param rx2 bottom right x of the rectangle
   * @param ry2 bottom right y of the rectangle
   * @param cx x position of the circle
   * @param cy y position of the circle
   * @param cr the squared radius of the circle
   * @return a vector specifying a random position
   */
  public static Vector2 newRandomTarget(Random rnd, int rx1, int ry1, int rx2, int ry2,
      int cx, int cy, int cr) {
    assert cr < ExtraMath.square(ry2 - ry1);

    float targetX;
    float targetY;

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
    targetX = rnd.nextInt(rx1, rx2);

    /* Now use the circle's equation:
     * (x - a)^2 + (y - b)^2 = r^2
     * => y = b + sqrt(r^2 - (x - a)^2)
     *
     * If r^2 is less than (x - a)^2, the root will become imaginary, thus all
     * y values lies outside of the circle for this x.
     */

    float tmp = ExtraMath.square(targetX - cx);
    if (cr < tmp) {
      // Circle is not intersecting our x here
      targetY = rnd.nextFloat(ry1, ry2);
    } else {
      // Here we have to take the circle into account
      int yRoot = (int) Math.sqrt(cr - tmp);

      // Top and bottom of the circle
      int y1 = cy - yRoot;
      int y2 = cy + yRoot + 1; // +1 so that we don't get point in the circle

      // Note that portions of the circle could lie outside of the rectangle.
      if (y2 < ry1 || y1 > ry2) {
        // Entire circle is outside of the rectangle
        targetY = rnd.nextInt(ry1, ry2);
      } else if (y1 < ry1) {
        // Top of circle is above the rectangle
        targetY = rnd.nextInt(y2, ry2);
      } else if (y2 > ry2) {
        // Bottom of circle is below the rectangle
        targetY = rnd.nextInt(ry1, y1);
      } else {
        // The part of the circle we're interested in is within the rectangle
        // We now have two intervals, note that the size of the two intervals
        // differ, we have to be careful so we get an uniform distribution.

        int topSize = y1 - ry1;
        int bottomSize = ry2 - y2;
        int totalSize = topSize + bottomSize;

        if (rnd.nextInt(totalSize) < topSize) {
          // Upper
          targetY = rnd.nextInt(ry1, y1);
        } else {
          // Lower
          targetY = rnd.nextInt(y2, ry2);
        }
      }
    }

    return new Vector2(targetX, targetY);
  }
}
