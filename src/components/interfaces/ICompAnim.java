/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package components.interfaces;

import org.newdawn.slick.Image;

import components.graphics.Tile;
import components.graphics.animations.IAnimator;

public interface ICompAnim extends ICompUpRend {
  Image getLastFrame();

  void goToFirstFrame();

  void setAnimator(final IAnimator animator);
  IAnimator getAnimator();

  int getTileWidth();
  int getTileHeight();

  Tile getTileCount();
  Tile getLastTile();
}
