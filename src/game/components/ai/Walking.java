/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.ai;

import game.components.Message;
import game.components.holdables.Hand;
import game.components.physics.Movement;
import game.entities.IEntity;
import game.pods.GameTime;
import main.Locator;
import math.ExMath;
import math.IRandom;
import math.Rectangle;
import math.Vector2;

public class Walking implements IBossState {
  public static final float MIN_WALK         = 100.0f;
  public static final float MIN_WALK_SQUARED = ExMath.square(MIN_WALK);

  private final IEntity entity;
  private final Hand hand;
  private final Rectangle body;
  private final float speed;
  private final Movement movement;
  private final Rectangle movementRect;

  private Vector2 target;
  private int targets;

  /**
   * Create a new walk state for boss AI.
   * @param entity the boss entity
   * @param hand the boss hand
   * @param movement the boss movement
   * @param speed the speed of the boss
   * @param movementRect the rectangle which the boss should move within
   * @param targets the number of targets (positions the boss should walk to)
   * @param initialTarget the first target the boss should reach
   */
  public Walking(IEntity entity, Hand hand, Movement movement, float speed,
      Rectangle movementRect, int targets, Vector2 initialTarget) {
    this.entity       = entity;
    this.body         = entity.getBody();
    this.hand         = hand;
    this.speed        = speed;
    this.movement     = movement;
    this.targets      = targets;
    this.movementRect = movementRect;
    this.target       = initialTarget;
  }

  /**
   * Create a new walk state for boss AI.
   * Instead of specifying the initial target a random target will be choosen
   * instead.
   * @param entity the boss entity
   * @param hand the boss hand
   * @param movement the boss movement
   * @param speed the speed of the boss
   * @param movementRect the rectangle which the boss should move within
   * @param targets the number of targets (positions the boss should walk to)
   */
  public Walking(IEntity entity, Hand hand, Movement movement, float speed,
      Rectangle movementRect, int targets) {
    this(entity, hand, movement, speed, movementRect, targets,
      newRandomTarget(Locator.getRandom(),
        movementRect, entity.getBody().getCenter(), MIN_WALK_SQUARED));
  }

  @Override
  public void start(GameTime time) {
    entity.sendMessage(Message.START_ANIMATION, null);
    hand.stopUse();

    headFor(target);
  }

  @Override
  public void update(GameTime time) {
    if (targets > 0) {
      Vector2 a = Vector2.subtract(body.getCenter(), target);
      if (Vector2.dot(a, movement.getVelocity()) >= 0) {
        --targets;
        // Target passed
        if (targets > 0) {
          target = newRandomTarget(
            Locator.getRandom(), movementRect, body.getCenter(), MIN_WALK_SQUARED);
          headFor(target);
        }
      }
    }
  }

  public Vector2 getTarget() {
    return target;
  }

  @Override
  public boolean isFinished() {
    return targets == 0;
  }

  @Override
  public BossState getNextState() {
    return BossState.SHOOTING;
  }

  private void headFor(Vector2 target) {
    // Subtract half size so that the center moves towards the target, instead
    // of the upper left.
    Vector2 newTarget = Vector2.subtract(target, body.getHalfSize());
    Vector2 delta     = Vector2.subtract(newTarget, body.getCenter());
    Vector2 direction = delta.normalize();
    Vector2 velocity  = Vector2.multiply(direction, speed);

    movement.setVelocity(velocity);
  }

  /**
   * Generate a random position that lies within a rectangle but outside of a
   * circle.
   * @param rnd the random generator to use
   * @param rect the rectangle
   * @param cPos the circle's position
   * @param radiusSquared the circle's radius squared
   * @return a vector specifying a random position
   */
  private static Vector2 newRandomTarget(IRandom rnd, Rectangle rect,
                                         Vector2 cPos, float radiusSquared) {
    assert radiusSquared < ExMath.square(rect.getHeight());

    float targetX, targetY;

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
    targetX = rnd.nextFloat(rect.getX1(), rect.getX2());

    /* Now use the circle's equation:
     * (x - a)^2 + (y - b)^2 = r^2
     * => y = b + sqrt(r^2 - (x - a)^2)
     *
     * If r^2 is less than (x - a)^2, the root will become imaginary, thus all
     * y values lies outside of the circle for this x.
     */

    float tmp = ExMath.square(targetX - cPos.x);
    if (radiusSquared < tmp) {
      // Circle is not intersecting our x here
      targetY = rnd.nextFloat(rect.getY1(), rect.getY2());
    } else {
      // Here we have to take the circle into account
      float yRoot = (float) (Math.sqrt(radiusSquared - tmp));

      float y1 = cPos.y - yRoot;
      float y2 = cPos.y + yRoot;

      // Note that portions of the circle could lie outside of the rectangle.
      // In that case we have to
      if (y1 < rect.getY1()) {
        targetY = rnd.nextFloat(y2, rect.getY2());
      } else if (y2 > rect.getY1()) {
        targetY = rnd.nextFloat(rect.getY1(), y1);
      } else {
        // Now we have two intervals
        if (rnd.nextBool()) {
          // Upper
          targetY = rnd.nextFloat(rect.getY1(), y1);
        } else {
          // Lower
          targetY = rnd.nextFloat(y2, rect.getY2());
        }
      }
    }

    return new Vector2(targetX, targetY);
  }
}
