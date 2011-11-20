/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics.animations;


public class Continuous implements IAnimator {
  private final Tile size;

  public Continuous(Tile size) {
    this.size = size;
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public Tile next(Tile tile) {
    int x = tile.x + 1;
    int y = tile.y;

    if (x >= size.x) {
      x = 0;
      ++y;

      if (y >= size.y) {
        y = 0;
      }
    }

    return new Tile(x, y);
  }
}
