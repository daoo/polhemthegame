/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.ai;

import game.components.LogicComponent;
import game.components.holdables.Hand;
import game.components.physics.Movement;
import game.entities.EntityImpl;
import game.misc.Locator;
import game.types.GameTime;
import game.types.Message;
import math.Aabb;
import math.ExtraMath;
import math.Rectangle;
import math.Vector2;

public class BossAI implements LogicComponent {
  private static final int INITIAL_TARGET_COUNT = 1;
  private static final int TARGET_MIN_COUNT = 1;
  private static final int TARGET_MAX_COUNT = 3;

  public static final float MIN_WALK = 100.0f;
  public static final float MIN_WALK_SQUARED = ExtraMath.square(MIN_WALK);

  private static final int SHOOTING_TIME_MIN = 1000;
  private static final int SHOOTING_TIME_MAX = 1500;

  private final EntityImpl mEntity;
  private final Movement mMovement;
  private final Hand mHand;
  private final Aabb mMovementBox;
  private final Vector2 mInitialTarget;
  private final float mSpeed;

  private BossState mState;

  public BossAI(
      EntityImpl entity, Movement movement, Hand hand, Aabb arenaBox, float locationX, float speed,
      Vector2 initialTarget) {
    mEntity = entity;
    mHand = hand;
    mMovement = movement;
    mSpeed = speed;
    mInitialTarget = initialTarget;

    // Setup the area which the TOP LEFT of the boss body may move around in
    float bossWidth = entity.getBody().getSize().x;
    float bossHeight = entity.getBody().getSize().y;
    float x1 = arenaBox.getMax().x - locationX;
    float y1 = arenaBox.getMin().y + bossHeight;
    float x2 = arenaBox.getMax().x - 3.0f / 2.0f * bossWidth;
    float y2 = arenaBox.getMax().y - bossHeight;

    Vector2 min = new Vector2(x1, y1);
    Vector2 max = new Vector2(x2, y2);
    mMovementBox = new Aabb(min, Rectangle.fromExtents(min, max));
  }

  public BossState getState() {
    return mState;
  }

  @Override
  public void update(GameTime time) {
    mState.update(time);

    if (mState.isFinished()) {
      // Go to next state

      if (mState instanceof Walking) {
        int shootingTime = Locator.getRandom().nextInt(SHOOTING_TIME_MIN, SHOOTING_TIME_MAX);
        mState = new Shooting(shootingTime);

        mMovement.setVelocity(Vector2.ZERO);
        mEntity.sendMessage(Message.STOP_ANIMATION, null);
        mHand.startUse();
      } else if (mState instanceof Shooting) {
        int targets = Locator.getRandom().nextInt(TARGET_MIN_COUNT, TARGET_MAX_COUNT + 1);
        mState = new Walking(mEntity.getBody(), mHand, mMovement, mSpeed, mMovementBox, targets);

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

      mState = new Walking(mEntity.getBody(), mHand, mMovement, mSpeed, mMovementBox,
          INITIAL_TARGET_COUNT, mInitialTarget);

      mEntity.sendMessage(Message.START_ANIMATION, null);

      mState.start(time);
    }
  }

  public Aabb getMovementRect() {
    return mMovementBox;
  }
}
