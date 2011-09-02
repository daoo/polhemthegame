/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package com.daoo.components.interfaces;

import org.newdawn.slick.Image;

import com.daoo.components.graphics.Tile;
import com.daoo.components.graphics.animations.IAnimator;

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
