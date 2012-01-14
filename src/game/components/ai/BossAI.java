/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.ai;

import game.components.Message;
import game.components.holdables.Hand;
import game.components.interfaces.ILogicComponent;
import game.components.physics.Movement;
import game.entities.IEntity;
import game.pods.GameTime;
import main.Locator;
import math.ExMath;
import math.Rectangle;
import math.Vector2;

public class BossAI implements ILogicComponent {
  private static final int INITIAL_TARGET_COUNT = 2;
  private static final int TARGET_MIN_COUNT     = 0;
  private static final int TARGET_MAX_COUNT     = 3;

  public static final float MIN_WALK         = 100.0f;
  public static final float MIN_WALK_SQUARED = ExMath.square(MIN_WALK);

  private static final float SHOOTING_TIME_MIN = 1.0f;
  private static final float SHOOTING_TIME_MAX = 1.0f;

  private final IEntity entity;
  private final Rectangle body;
  private final Movement movement;
  private final Hand hand;
  private final Rectangle movementRect;
  private final Vector2 initialTarget;
  private final float speed;

  private IBossState state;

  public BossAI(IEntity entity, Movement movement, Hand hand, Rectangle arenaRect,
                float locationX, float speed, Vector2 initialTarget) {
    this.entity        = entity;
    this.hand          = hand;
    this.movement      = movement;
    this.body          = entity.getBody();
    this.speed         = speed;
    this.initialTarget = initialTarget;

    Vector2 halfSize = body.getHalfSize();

    float x1 = arenaRect.getX2() + halfSize.x - locationX;
    float y1 = arenaRect.getY1() + halfSize.y;
    float x2 = arenaRect.getX2() - halfSize.x;
    float y2 = arenaRect.getY2() - halfSize.y;

    this.movementRect = new Rectangle(x1, y1, x2 - x1, y2 - y1);
  }

  public IBossState getState() {
    return state;
  }

  @Override
  public void update(GameTime time) {
    state.update(time);

    if (state.isFinished()) {
      // Go to next state

      if (state.getNextState() == BossState.WALKING) {
        int targetCount = Locator.getRandom().nextInt(
          TARGET_MIN_COUNT, TARGET_MAX_COUNT + 1);
        state = new Walking(
          entity, hand, movement, speed, movementRect, targetCount);
      } else if (state.getNextState() == BossState.SHOOTING) {
        float shootingTime = Locator.getRandom().nextFloat(
          SHOOTING_TIME_MIN, SHOOTING_TIME_MAX);
        state = new Shooting(entity, hand, shootingTime);
      }

      state.start(time);
    }
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    if (message == Message.START_BOSS) {
      GameTime time = (GameTime) args;

      state = new Walking(entity, hand, movement, speed, movementRect,
        INITIAL_TARGET_COUNT, initialTarget);
      state.start(time);
    }
  }

  public Rectangle getMovementRect() {
    return movementRect;
  }
}
