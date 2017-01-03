/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.ai;

import game.components.LogicComponent;
import game.components.holdables.Hand;
import game.components.physics.Movement;
import game.entities.EntityImpl;
import game.types.GameTime;
import game.types.Message;
import math.Aabb;
import math.Rectangle;
import math.Vector2;

// TODO: Better naming
public class BossAI implements LogicComponent {
  private static final int INITIAL_TARGET_COUNT = 1;

  // TODO: Separate class?
  static class Boss {
    final EntityImpl entity;
    final Movement movement;
    final Hand hand;
    final Aabb boundary;
    final float speed;

    Boss(EntityImpl entity, Movement movement, Hand hand, Aabb boundary, float speed) {
      this.entity = entity;
      this.movement = movement;
      this.hand = hand;
      this.boundary = boundary;
      this.speed = speed;
    }
  }

  private final Boss mBoss;
  private final Vector2 mInitialTarget;
  private BossState mState = null;

  public BossAI(
      EntityImpl entity, Movement movement, Hand hand, Aabb arenaBox, float locationX, float speed,
      Vector2 initialTarget) {
    // Setup the area which the TOP LEFT of the boss body may move around in
    float bossWidth = entity.getBody().getSize().x;
    float bossHeight = entity.getBody().getSize().y;
    float x1 = arenaBox.getMax().x - locationX;
    float y1 = arenaBox.getMin().y + bossHeight;
    float x2 = arenaBox.getMax().x - 3.0f / 2.0f * bossWidth;
    float y2 = arenaBox.getMax().y - bossHeight;

    Vector2 min = new Vector2(x1, y1);
    Vector2 max = new Vector2(x2, y2);
    Aabb movementBox = new Aabb(min, Rectangle.fromExtents(min, max));
    mBoss = new Boss(entity, movement, hand, movementBox, speed);
    mInitialTarget = initialTarget;
  }

  public BossState getState() {
    return mState;
  }

  @Override
  public void update(GameTime time) {
    if (mState != null) {
      BossState next = mState.update(time);
      if (next != null) {
        mState = next;
      }
    }
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    if (message == Message.START_BOSS) {
      GameTime time = (GameTime) args;

      mState = new Walking(mBoss, INITIAL_TARGET_COUNT, mInitialTarget);
      mState.start(time);
    }
  }

  public Aabb getMovementRect() {
    return mBoss.boundary;
  }
}
