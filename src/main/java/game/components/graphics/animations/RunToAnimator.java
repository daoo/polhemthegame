/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics.animations;


public class RunToAnimator implements Animator {
  private final int mCount;
  private final int mTarget;
  private boolean mFinished;

  public RunToAnimator(int count, int target) {
    assert count > 0;
    assert target >= 0;

    mCount = count;
    mTarget = target;
    mFinished = false;
  }

  @Override
  public boolean isFinished() {
    return mFinished;
  }

  @Override
  public int next(int index) {
    if (mFinished) {
      return index;
    }

    int next = (index + 1) % mCount;
    mFinished = next == mTarget;
    return next;
  }
}
