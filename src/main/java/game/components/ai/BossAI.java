/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.ai;

import game.components.ILogicComponent;
import game.components.holdables.Hand;
import game.components.physics.Movement;
import game.entities.Entity;
import game.misc.Locator;
import game.types.GameTime;
import game.types.Message;
import math.ExtraMath;
import math.Rectangle;
import math.Vector2;

public class BossAI implements ILogicComponent {
  private static final int INITIAL_TARGET_COUNT = 1;
  private static final int TARGET_MIN_COUNT     = 1;
  private static final int TARGET_MAX_COUNT     = 3;

  public static final float MIN_WALK         = 100.0f;
  public static final float MIN_WALK_SQUARED = ExtraMath.square(MIN_WALK);

  private static final int SHOOTING_TIME_MIN = 1000;
  private static final int SHOOTING_TIME_MAX = 1500;

  private final Entity entity;
  private final Movement movement;
  private final Hand hand;
  private final Rectangle movementRect;
  private final Vector2 initialTarget;
  private final float speed;

  private IBossState state;

  public BossAI(Entity entity, Movement movement, Hand hand, Rectangle arenaRect,
      float locationX, float speed, Vector2 initialTarget) {
    this.entity        = entity;
    this.hand          = hand;
    this.movement      = movement;
    this.speed         = speed;
    this.initialTarget = initialTarget;

    // Setup the area which the TOP LEFT of the boss body may move around in
    float x1 = arenaRect.getX2() - locationX;
    float y1 = arenaRect.getY1() + entity.body.getHeight();
    float x2 = arenaRect.getX2() - 3.0f / 2.0f * entity.body.getWidth();
    float y2 = arenaRect.getY2() - entity.body.getHeight();

    this.movementRect = new Rectangle(x1, y1, (int) (x2 - x1), (int) (y2 - y1));
  }

  public IBossState getState() {
    return state;
  }

  @Override
  public void update(GameTime time) {
    state.update(time);

    if (state.isFinished()) {
      // Go to next state

      if (state.getState() == BossState.WALKING) {
        int shootingTime = Locator.getRandom().nextInt(
            SHOOTING_TIME_MIN, SHOOTING_TIME_MAX);
        state = new Shooting(shootingTime);

        movement.setVelocity(Vector2.ZERO);
        entity.sendMessage(Message.STOP_ANIMATION, null);
        hand.startUse();
      } else if (state.getState() == BossState.SHOOTING) {
        int targets = Locator.getRandom().nextInt(
            TARGET_MIN_COUNT, TARGET_MAX_COUNT + 1);
          state = new Walking(entity.body, hand, movement, speed, movementRect, targets);

          entity.sendMessage(Message.START_ANIMATION, null);
          hand.stopUse();
      }

      state.start(time);
    }
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    if (message == Message.START_BOSS) {
      GameTime time = (GameTime) args;

      state = new Walking(entity.body, hand, movement, speed, movementRect,
        INITIAL_TARGET_COUNT, initialTarget);

      entity.sendMessage(Message.START_ANIMATION, null);

      state.start(time);
    }
  }

  public Rectangle getMovementRect() {
    return movementRect;
  }
}
