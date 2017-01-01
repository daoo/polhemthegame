/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics.animations;

import game.misc.Locator;

public class RandomAnimator implements Animator {
  private final int mCount;

  public RandomAnimator(int count) {
    assert count > 0;
    mCount = count;
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public int next(int index) {
    return Locator.getRandom().nextInt(mCount);
  }
}
