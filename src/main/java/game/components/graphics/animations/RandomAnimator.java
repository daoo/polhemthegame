/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics.animations;

import game.misc.Locator;
import util.Random;

public class RandomAnimator implements Animator {
  private final Tile mSize;

  public RandomAnimator(Tile size) {
    assert size != null;
    mSize = size;
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public Tile next(Tile tile) {
    Random rnd = Locator.getRandom();
    return new Tile(rnd.nextInt(mSize.x), rnd.nextInt(mSize.y));
  }
}
