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
import org.newdawn.slick.Image;


public class TexturedQuad implements ICompAnim {
  private final Image img;

  public TexturedQuad(final Image img) {
    this.img = img;
  }

  @Override
  public void update(final GameTime time) {
    // Do nothing
  }

  @Override
  public void render(final Graphics g) {
    g.drawImage(img, 0, 0);
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
  public int getTileWidth() {
    return img.getWidth();
  }

  @Override
  public int getTileHeight() {
    return img.getHeight();
  }

  @Override
  public Tile getTileCount() {
    return new Tile(1, 1);
  }

  @Override
  public IAnimator getAnimator() {
    return new Idle();
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
