/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics;

import game.components.graphics.animations.Continuous;
import game.components.graphics.animations.IAnimator;
import game.components.graphics.animations.Idle;
import game.components.graphics.animations.Tile;
import game.components.interfaces.IAnimatedComponent;
import game.misc.Clock;
import game.types.GameTime;
import game.types.Message;
import game.types.Orientation;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import util.SpriteSheet;

/**
 * Component for animating a sprite sheet.
 */
public class AnimatedSheet implements IAnimatedComponent {
  private static final IAnimator IDLE = new Idle();

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

  @Override
  public IAnimator getAnimator() {
    return animator;
  }

  public Image getCurrentFrame() {
    return sheet.getSubImage(current.x, current.y);
  }

  @Override
  public Tile getCurrentTile() {
    return current;
  }

  @Override
  public Tile getFirstTile() {
    return Tile.ZERO;
  }

  @Override
  public Tile getLastTile() {
    return new Tile(size.x - 1, size.y - 1);
  }

  public SpriteSheet getSpriteSheet() {
    return sheet;
  }

  @Override
  public Tile getTileCount() {
    return size;
  }

  @Override
  public int getTileHeight() {
    return sheet.getTileHeight();
  }

  @Override
  public int getTileWidth() {
    return sheet.getTileWidth();
  }

  @Override
  public void goToFirstFrame() {
    current = Tile.ZERO;
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    if (message == Message.START_ANIMATION) {
      animator = new Continuous(getTileCount());
    } else if (message == Message.STOP_ANIMATION) {
      if (!animator.isFinished()) {
        goToFirstFrame();
        animator = new Idle();
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

      // Note that when flipped, the x offset should be flipped to
      g.translate(-offsetX, offsetY);
    } else {
      g.translate(offsetX, offsetY);
    }

    g.drawImage(sheet.getSubImage(current.x, current.y), 0, 0);

    g.popTransform();
  }

  @Override
  public void setAnimator(IAnimator animator) {
    this.animator = animator;
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
        size.x, size.y, getTileWidth(), getTileHeight());
  }
}
