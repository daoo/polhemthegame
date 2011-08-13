/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package components.graphics.animations;

import components.graphics.Tile;

public class RunTo implements IAnimator {
  private boolean finished;
  private final Tile size, target;

  public RunTo(final Tile size, final Tile target) {
    this.size = size;
    this.target = target;
    finished = false;
  }

  @Override
  public boolean isFinished() {
    return finished;
  }

  @Override
  public Tile next(final Tile tile) {
    if (!finished) {
      int x = tile.x + 1;
      int y = tile.y;

      if (x >= size.x) {
        x = 0;
        ++y;

        if (y >= size.y) {
          y = 0;
        }
      }

      final Tile result = new Tile(x, y);
      if (target.sameAs(result)) {
        finished = true;
      }

      return result;
    }
    else {
      return tile;
    }
  }
}
