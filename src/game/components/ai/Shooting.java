/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.ai;

import game.components.Message;
import game.components.holdables.Hand;
import game.entities.IEntity;
import game.pods.GameTime;

public class Shooting implements IBossState {
  private final IEntity entity;
  private final Hand hand;
  private final float length;

  private float endTime;
  private boolean finished;

  public Shooting(IEntity entity, Hand hand, float length) {
    this.entity = entity;
    this.hand   = hand;
    this.length = length;

    endTime  = 0;
    finished = false;
  }

  @Override
  public void start(GameTime time) {
    endTime = time.elapsed + length;
    entity.sendMessage(Message.STOP_ANIMATION, null);
    hand.startUse();
  }

  @Override
  public void update(GameTime time) {
    if (time.elapsed >= endTime) {
      finished = true;
    }
  }

  @Override
  public boolean isFinished() {
    return finished;
  }

  @Override
  public BossState getNextState() {
    return BossState.WALKING;
  }
}
