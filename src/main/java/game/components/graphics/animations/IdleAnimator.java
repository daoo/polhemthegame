/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics.animations;

public class IdleAnimator implements Animator {
  @Override
  public boolean isFinished() {
    return true;
  }

  @Override
  public int next(int index) {
    return index;
  }
}
