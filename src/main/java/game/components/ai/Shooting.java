/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.ai;

import game.misc.Locator;
import game.types.GameTime;
import game.types.Message;
import math.Vector2;

public class Shooting implements BossState {
  private static final int SHOOTING_TIME_MIN = 1000;
  private static final int SHOOTING_TIME_MAX = 1500;

  private final BossAI.Boss mBoss;
  private final long mLength;
  private long mEndTime;

  public Shooting(BossAI.Boss boss) {
    this(boss, Locator.getRandom().nextInt(SHOOTING_TIME_MIN, SHOOTING_TIME_MAX));
  }

  public Shooting(BossAI.Boss boss, int length) {
    mBoss = boss;
    mLength = length;
    mEndTime = 0;
  }

  @Override
  public void start(GameTime time) {
    mEndTime = time.elapsedMilli + mLength;

    mBoss.movement.setVelocity(Vector2.ZERO);
    mBoss.entity.sendMessage(Message.STOP_ANIMATION, null);
    mBoss.hand.startUse();
  }

  @Override
  public BossState update(GameTime time) {
    if (time.elapsedMilli >= mEndTime) {
      Walking walking = new Walking(mBoss);
      walking.start(time);
      return walking;
    } else {
      return null;
    }
  }
}
