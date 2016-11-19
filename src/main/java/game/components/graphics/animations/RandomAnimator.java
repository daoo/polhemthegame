/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics.animations;

import game.misc.Locator;
import util.Random;

public class RandomAnimator implements IAnimator {
  private final Tile size;

  public RandomAnimator(Tile size) {
    assert size != null;
    this.size = size;
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public Tile next(Tile tile) {
    Random rnd = Locator.getRandom();
    return new Tile(rnd.nextInt(size.x), rnd.nextInt(size.y));
  }
}
