/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics.animations;


public class ContinuousAnimator implements Animator {
  private final Tile mSize;

  public ContinuousAnimator(Tile size) {
    assert size != null;
    mSize = size;
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public Tile next(Tile tile) {
    int x = tile.x + 1;
    int y = tile.y;

    if (x >= mSize.x) {
      x = 0;
      ++y;

      if (y >= mSize.y) {
        y = 0;
      }
    }

    return new Tile(x, y);
  }
}
