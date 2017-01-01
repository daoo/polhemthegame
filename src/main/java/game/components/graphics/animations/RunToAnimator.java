/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics.animations;


public class RunToAnimator implements Animator {
  private boolean mFinished;
  private final Tile mSize;
  private final Tile mTarget;

  public RunToAnimator(Tile size, Tile target) {
    assert size != null;
    assert target != null;

    mSize = size;
    mTarget = target;
    mFinished = false;
  }

  @Override
  public boolean isFinished() {
    return mFinished;
  }

  @Override
  public Tile next(Tile tile) {
    if (mFinished) {
      return tile;
    }

    int x = tile.x + 1;
    int y = tile.y;

    if (x >= mSize.x) {
      x = 0;
      ++y;

      if (y >= mSize.y) {
        y = 0;
      }
    }

    Tile result = new Tile(x, y);
    if (mTarget.isEqual(result)) {
      mFinished = true;
    }

    return result;
  }
}
