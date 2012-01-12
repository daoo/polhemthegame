/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.ai;

import game.components.Message;
import game.components.holdables.Hand;
import game.components.physics.Movement;
import game.entities.IEntity;
import game.pods.GameTime;

import java.util.Queue;

import math.Rectangle;
import math.Vector2;

public class Walking implements IBossState {
  private final IEntity entity;
  private final Hand hand;
  private final Rectangle body;
  private final float speed;
  private final Movement movement;
  private final Queue<Vector2> targets;

  public Walking(IEntity entity, Hand hand, float speed, Movement movement, Queue<Vector2> targets) {
    if (targets.size() <= 1) {
      throw new IllegalArgumentException("Can't walk without targets");
    }

    this.entity   = entity;
    this.body     = entity.getBody();
    this.hand     = hand;
    this.speed    = speed;
    this.movement = movement;
    this.targets  = targets;
  }

  @Override
  public void start(GameTime time) {
    entity.sendMessage(Message.START_ANIMATION, null);
    hand.stopUse();

    headFor(targets.peek());
  }

  private void headFor(Vector2 target) {
    Vector2 delta     = Vector2.subtract(target, body.getMin());
    Vector2 direction = delta.normalize();
    Vector2 velocity  = Vector2.multiply(direction, speed);

    movement.setVelocity(velocity);
  }

  @Override
  public void update(GameTime time) {
    if (!targets.isEmpty()) {
      Vector2 a = Vector2.subtract(body.getMin(), targets.peek());
      if (Vector2.dot(a, movement.getVelocity()) >= 0) {
        // Target passed
        targets.remove();
        if (!targets.isEmpty()) {
          headFor(targets.peek());
        }
      }
    }
  }

  @Override
  public boolean isFinished() {
    return targets.isEmpty();
  }

  @Override
  public BossState getNextState() {
    return BossState.SHOOTING;
  }
}
