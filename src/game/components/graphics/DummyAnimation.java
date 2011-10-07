/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics;

import game.components.ComponentMessages;
import game.components.graphics.animations.IAnimator;
import game.components.graphics.animations.Idle;
import game.components.interfaces.ICompAnim;
import math.time.GameTime;

import org.newdawn.slick.Graphics;


public class DummyAnimation implements ICompAnim {
  public DummyAnimation() {
    // Do nothing
  }

  @Override
  public void update(final GameTime time) {
    // Do nothing
  }

  @Override
  public void render(final Graphics g) {
    // Do nothing
  }

  @Override
  public void goToFirstFrame() {
    // Do nothing
  }

  @Override
  public void setAnimator(final IAnimator animator) {
    // Do nothing
  }

  @Override
  public IAnimator getAnimator() {
    return new Idle();
  }

  @Override
  public int getTileWidth() {
    return 0;
  }

  @Override
  public int getTileHeight() {
    return 0;
  }

  @Override
  public Tile getTileCount() {
    return Tile.ZERO;
  }

  @Override
  public Tile getLastTile() {
    return Tile.ZERO;
  }

  @Override
  public void reciveMessage(final ComponentMessages message) {
    // Do nothing
  }
}
