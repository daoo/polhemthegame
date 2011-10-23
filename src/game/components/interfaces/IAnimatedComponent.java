/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.interfaces;

import game.components.graphics.animations.IAnimator;
import game.components.graphics.animations.Tile;

public interface IAnimatedComponent extends IRenderComponent {
  void goToFirstFrame();

  void setAnimator(IAnimator animator);
  IAnimator getAnimator();

  int getTileWidth();
  int getTileHeight();

  Tile getTileCount();
  Tile getLastTile();
}
