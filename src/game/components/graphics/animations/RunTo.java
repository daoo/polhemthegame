/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics.animations;


public class RunTo implements IAnimator {
  private boolean finished;
  private final Tile size, target;

  public RunTo(Tile size, Tile target) {
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

      Tile result = new Tile(x, y);
      if (target.equals(result)) {
        finished = true;
      }

      return result;
    }
    else {
      return tile;
    }
  }
}
