/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics;

import game.components.IRenderComponent;
import game.components.graphics.animations.ContinuousAnimator;
import game.components.graphics.animations.IAnimator;
import game.components.graphics.animations.IdleAnimator;
import game.components.graphics.animations.Tile;
import game.types.GameTime;
import game.types.Message;
import game.types.Orientation;

import org.newdawn.slick.Graphics;

import util.Clock;
import util.SpriteSheet;

/**
 * Component for animating a sprite sheet.
 */
public class AnimatedSheet implements IRenderComponent {
  private static final IAnimator IDLE = new IdleAnimator();

  private final int offsetX, offsetY;
  private final float rotation;
  private final boolean flip;

  private final SpriteSheet sheet;
  private final int centerX, centerY;
  private final Tile size;

  private final Clock clock;

  private IAnimator animator;
  private Tile current;

  /**
   * Create a new animated sprite sheet.
   * @param targetFrameRate the frame rate of the animation, greater than zero
   * @param offsetX translation before rendering
   * @param offsetY translation before rendering
   * @param orientation the orientation of the rendered image, if we want left
   *        orientation the image will be mirrored along the x-axis.
   * @param rotation rotation before rendering (degrees)
   * @param sheet the sprite sheet to animate, not null
   */
  public AnimatedSheet(float targetFrameRate, int offsetX, int offsetY,
      Orientation orientation, int rotation, SpriteSheet sheet) {
    assert targetFrameRate > 0;
    assert sheet != null;

    this.sheet = sheet;

    this.offsetX = offsetX;
    this.offsetY = offsetY;
    this.flip = orientation == Orientation.LEFT;
    this.rotation = rotation;

    size = new Tile(sheet.getTileCountX(), sheet.getTileCountY());
    centerX = sheet.getTileWidth() / 2;
    centerY = sheet.getTileWidth() / 2;

    current  = Tile.ZERO;
    animator = IDLE;

    clock = new Clock((int) (1000 / targetFrameRate));
  }

  public Tile getCurrentTile() {
    return current;
  }

  public Tile getLastTile() {
    return new Tile(size.x - 1, size.y - 1);
  }

  public SpriteSheet getSpriteSheet() {
    return sheet;
  }

  public Tile getTileCount() {
    return size;
  }

  public void setAnimator(IAnimator animator) {
    this.animator = animator;
  }

  @Override
  public int getHeight() {
    return sheet.getTileHeight();
  }

  @Override
  public int getWidth() {
    return sheet.getTileWidth();
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    if (message == Message.START_ANIMATION) {
      animator = new ContinuousAnimator(getTileCount());
    } else if (message == Message.STOP_ANIMATION) {
      if (!animator.isFinished()) {
        current = Tile.ZERO;
        animator = new IdleAnimator();
      }
    }
  }

  @Override
  public void render(Graphics g) {
    g.pushTransform();
    g.rotate(centerX, centerY, rotation);

    if (flip) {
      // Be sure to flip around the center
      g.translate(centerX, 0);
      g.scale(-1, 1);
      g.translate(-centerX, 0);
      // Alternatively, translate by width after flip
    }

    g.translate(offsetX, offsetY);

    g.drawImage(sheet.getSubImage(current.x, current.y), 0, 0);

    g.popTransform();
  }

  @Override
  public void update(GameTime time) {
    if (!animator.isFinished() && clock.needsSync(time.elapsedMilli)) {
      clock.sync(time.elapsedMilli);

      current = animator.next(current);
    }
  }

  @SuppressWarnings("boxing")
  @Override
  public String toString() {
    return String.format("AnimatedSheet - count: %dx%d, size: %dx%d",
        size.x, size.y, getWidth(), getHeight());
  }
}
