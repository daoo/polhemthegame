/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics.animations;


public class ContinuousAnimator implements Animator {
  private final int mCount;

  public ContinuousAnimator(int count) {
    assert count > 0;
    mCount = count;
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public int next(int index) {
    return (index + 1) % mCount;
  }
}
