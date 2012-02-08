/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.ai;

import game.types.GameTime;

public class Shooting implements IBossState {
  private final long length;

  private long endTime;
  private boolean finished;

  public Shooting(int length) {
    this.length = length;

    endTime  = 0;
    finished = false;
  }

  @Override
  public void start(GameTime time) {
    endTime = time.elapsedMilli + length;
  }

  @Override
  public void update(GameTime time) {
    if (time.elapsedMilli >= endTime) {
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
