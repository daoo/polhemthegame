/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics.animations;

import main.Locator;
import math.IRandom;

public class RandomAnimator implements IAnimator {
  private final Tile size;

  public RandomAnimator(Tile size) {
    assert size != null;
    this.size = size;
  }

  @Override
  public boolean isFinished() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public Tile next(Tile tile) {
    IRandom rnd = Locator.getRandom();
    return new Tile(rnd.nextInt(size.x), rnd.nextInt(size.y));
  }
}
