/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics.animations;

public interface Animator {
  boolean isFinished();

  Tile next(Tile tile);
}
