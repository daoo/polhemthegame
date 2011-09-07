/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics.animations;

import game.components.graphics.Tile;

public interface IAnimator {
  public boolean isFinished();

  public Tile next(final Tile tile);
}
