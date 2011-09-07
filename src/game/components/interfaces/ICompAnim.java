/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.interfaces;

import game.components.graphics.Tile;
import game.components.graphics.animations.IAnimator;

public interface ICompAnim extends ICompUpRend {
  void goToFirstFrame();

  void setAnimator(final IAnimator animator);
  IAnimator getAnimator();

  int getTileWidth();
  int getTileHeight();

  Tile getTileCount();
  Tile getLastTile();
}
