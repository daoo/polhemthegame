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
  private static final int TARGET_MIN_COUNT = 1;
  private static final int TARGET_MAX_COUNT = 3;

  public static final float MIN_WALK = 100.0f;
  public static final float MIN_WALK_SQUARED = ExtraMath.square(MIN_WALK);

  private static final int SHOOTING_TIME_MIN = 1000;
  private static final int SHOOTING_TIME_MAX = 1500;

  private final Entity mEntity;
  private final Movement mMovement;
  private final Hand mHand;
  private final Rectangle mMovementRect;
  private final Vector2 mInitialTarget;
  private final float mSpeed;

  private IBossState mState;

  public BossAI(
      Entity entity, Movement movement, Hand hand, Rectangle arenaRect, float locationX,
      float speed, Vector2 initialTarget) {
    mEntity = entity;
    mHand = hand;
    mMovement = movement;
    mSpeed = speed;
    mInitialTarget = initialTarget;

    // Setup the area which the TOP LEFT of the boss body may move around in
    float x1 = arenaRect.getX2() - locationX;
    float y1 = arenaRect.getY1() + entity.body.getHeight();
    float x2 = arenaRect.getX2() - 3.0f / 2.0f * entity.body.getWidth();
    float y2 = arenaRect.getY2() - entity.body.getHeight();

    mMovementRect = new Rectangle(x1, y1, (int) (x2 - x1), (int) (y2 - y1));
  }

  public IBossState getState() {
    return mState;
  }

  @Override
  public void update(GameTime time) {
    mState.update(time);

    if (mState.isFinished()) {
      // Go to next state

      if (mState.getState() == BossState.WALKING) {
        int shootingTime = Locator.getRandom().nextInt(SHOOTING_TIME_MIN, SHOOTING_TIME_MAX);
        mState = new Shooting(shootingTime);

        mMovement.setVelocity(Vector2.ZERO);
        mEntity.sendMessage(Message.STOP_ANIMATION, null);
        mHand.startUse();
      } else if (mState.getState() == BossState.SHOOTING) {
        int targets = Locator.getRandom().nextInt(TARGET_MIN_COUNT, TARGET_MAX_COUNT + 1);
        mState = new Walking(mEntity.body, mHand, mMovement, mSpeed, mMovementRect, targets);

        mEntity.sendMessage(Message.START_ANIMATION, null);
        mHand.stopUse();
      }

      mState.start(time);
    }
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    if (message == Message.START_BOSS) {
      GameTime time = (GameTime) args;

      mState = new Walking(
          mEntity.body, mHand, mMovement, mSpeed, mMovementRect, INITIAL_TARGET_COUNT,
          mInitialTarget);

      mEntity.sendMessage(Message.START_ANIMATION, null);

      mState.start(time);
    }
  }

  public Rectangle getMovementRect() {
    return mMovementRect;
  }
}
