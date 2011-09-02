/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package components.graphics;

import math.time.GameTime;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import components.ComponentMessages;
import components.graphics.animations.IAnimator;
import components.graphics.animations.Idle;
import components.interfaces.ICompAnim;

public class DummyAnimation implements ICompAnim {

  @Override
  public void update(GameTime time) {
    // Do nothing
  }

  @Override
  public void render(Graphics g) {
    // Do nothing
  }

  @Override
  public Image getLastFrame() {
    return null;
  }

  @Override
  public void goToFirstFrame() {
    // Do nothing
  }

  @Override
  public void setAnimator(IAnimator animator) {
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
  public void reciveMessage(ComponentMessages message) {
    // Do nothing
  }
}
