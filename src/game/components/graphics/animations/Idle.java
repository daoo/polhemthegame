/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics.animations;


public class Idle implements IAnimator {
  public Idle() {
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
