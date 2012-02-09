/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics;

import game.components.graphics.animations.IAnimator;
import game.components.graphics.animations.Idle;
import game.components.graphics.animations.Tile;
import game.components.interfaces.IAnimatedComponent;
import game.types.GameTime;
import game.types.Message;
import game.types.Orientation;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;


public class TexturedQuad implements IAnimatedComponent {
  private final static Tile TILE_SIZE     = new Tile(1, 1);
  private final static IAnimator ANIMATOR = new Idle();

  private final int centerX, centerY;
  private final boolean flip;
  private final int angle;
  private final Image img;

  public TexturedQuad(Image img, Orientation orientation, int angle) {
    this.img = img;
    this.flip = orientation == Orientation.LEFT;
    this.angle = angle;

    centerX = img.getWidth() / 2;
    centerY = img.getHeight() / 2;
  }

  @Override
  public IAnimator getAnimator() {
    return ANIMATOR;
  }

  @Override
  public Tile getCurrentTile() {
    return Tile.ZERO;
  }

  @Override
  public Tile getLastTile() {
    return Tile.ZERO;
  }

  @Override
  public Tile getTileCount() {
    return TILE_SIZE;
  }

  @Override
  public int getTileHeight() {
    return img.getHeight();
  }

  @Override
  public int getTileWidth() {
    return img.getWidth();
  }

  @Override
  public void goToFirstFrame() {
    // Do nothing
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    // Do nothing
  }

  @Override
  public void render(Graphics g) {
    g.pushTransform();

    if (flip) {
      // Be sure to flip around the center
      g.translate(centerX, 0);
      g.scale(-1, 1);
      g.translate(-centerX, 0);
      // Alternatively, translate by width after flip
    }
    g.rotate(centerX, centerY, angle);

    g.drawImage(img, 0, 0);
    g.popTransform();
  }

  @Override
  public void setAnimator(IAnimator animator) {
    // Do nothing
  }

  @Override
  public void update(GameTime time) {
    // Do nothing
  }
}
