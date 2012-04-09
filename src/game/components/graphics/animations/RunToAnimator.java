/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics.animations;


public class RunToAnimator implements IAnimator {
  private boolean finished;
  private final Tile size, target;

  public RunToAnimator(Tile size, Tile target) {
    assert size != null;
    assert target != null;

    this.size = size;
    this.target = target;
    finished = false;
  }

  @Override
  public boolean isFinished() {
    return finished;
  }

  @Override
  public Tile next(Tile tile) {
    if (finished) {
      return tile;
    }

    int x = tile.x + 1;
    int y = tile.y;

    if (x >= size.x) {
      x = 0;
      ++y;

      if (y >= size.y) {
        y = 0;
      }
    }

    Tile result = new Tile(x, y);
    if (target.isEqual(result)) {
      finished = true;
    }

    return result;
  }
}