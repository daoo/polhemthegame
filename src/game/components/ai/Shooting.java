/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.ai;

import game.types.GameTime;

public class Shooting implements IBossState {
  private final float length;

  private float endTime;
  private boolean finished;

  public Shooting(float length) {
    this.length = length;

    endTime  = 0;
    finished = false;
  }

  @Override
  public void start(GameTime time) {
    endTime = time.elapsed + length;
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
