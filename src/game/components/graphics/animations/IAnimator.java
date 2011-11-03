/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics.animations;


public interface IAnimator {
  boolean isFinished();

  Tile next(Tile tile);
}
