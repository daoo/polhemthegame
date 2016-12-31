/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.ai;

import game.types.GameTime;

public class Shooting implements IBossState {
  private final long mLength;

  private long mEndTime;
  private boolean mFinished;

  public Shooting(int length) {
    mLength = length;

    mEndTime = 0;
    mFinished = false;
  }

  @Override
  public void start(GameTime time) {
    mEndTime = time.elapsedMilli + mLength;
  }

  @Override
  public void update(GameTime time) {
    if (time.elapsedMilli >= mEndTime) {
      mFinished = true;
    }
  }

  @Override
  public boolean isFinished() {
    return mFinished;
  }

  @Override
  public BossState getState() {
    return BossState.SHOOTING;
  }
}
