/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics.animations;


public class IdleAnimator implements IAnimator {
  public IdleAnimator() {
  }

  @Override
  public boolean isFinished() {
    return true;
  }

  @Override
  public Tile next(Tile tile) {
    return tile;
  }
}
