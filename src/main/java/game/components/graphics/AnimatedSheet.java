/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics;

import org.newdawn.slick.Graphics;

import game.components.RenderComponent;
import game.components.graphics.animations.Animator;
import game.components.graphics.animations.ContinuousAnimator;
import game.components.graphics.animations.IdleAnimator;
import game.types.GameTime;
import game.types.Message;
import game.types.Orientation;
import util.Clock;
import util.SpriteSheet;

/**
 * Component for animating a sprite sheet.
 */
public class AnimatedSheet implements RenderComponent {
  private static final Animator IDLE = new IdleAnimator();

  private final int mOffsetX;
  private final int mOffsetY;
  private final float mRotation;
  private final boolean mFlip;

  private final SpriteSheet mSheet;
  private final int mCenterX;
  private final int mCenterY;

  private final Clock mClock;

  private Animator mAnimator;
  private int mCurrent;

  /**
   * Create a new animated sprite sheet.
   *
   * @param targetFrameRate the frame rate of the animation, greater than zero
   * @param offsetX translation before rendering
   * @param offsetY translation before rendering
   * @param orientation the orientation of the rendered image, if we want left
   * orientation the image will be mirrored along the x-axis.
   * @param rotation rotation before rendering (degrees)
   * @param sheet the sprite sheet to animate, not null
   */
  public AnimatedSheet(
      float targetFrameRate, int offsetX, int offsetY, Orientation orientation, int rotation,
      SpriteSheet sheet) {
    assert targetFrameRate > 0;
    assert sheet != null;

    mSheet = sheet;

    mOffsetX = offsetX;
    mOffsetY = offsetY;
    mFlip = orientation == Orientation.LEFT;
    mRotation = rotation;

    mCenterX = sheet.getTileWidth() / 2;
    mCenterY = sheet.getTileWidth() / 2;

    mCurrent = 0;
    mAnimator = IDLE;

    mClock = new Clock((int) (1000 / targetFrameRate));
  }

  public boolean isFinished() {
    return mAnimator.isFinished();
  }

  public int getTileCount() {
    return mSheet.getTileCount();
  }

  public void setAnimator(Animator animator) {
    mAnimator = animator;
  }

  public int getHeight() {
    return mSheet.getTileHeight();
  }

  public int getWidth() {
    return mSheet.getTileWidth();
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    if (message == Message.START_ANIMATION) {
      mAnimator = new ContinuousAnimator(mSheet.getTileCount());
    } else if (message == Message.STOP_ANIMATION) {
      if (!mAnimator.isFinished()) {
        mCurrent = 0;
        mAnimator = new IdleAnimator();
      }
    }
  }

  @Override
  public void render(Graphics g) {
    g.pushTransform();
    g.rotate(mCenterX, mCenterY, mRotation);

    if (mFlip) {
      // Be sure to mFlip around the center
      g.translate(mCenterX, 0);
      g.scale(-1, 1);
      g.translate(-mCenterX, 0);
      // Alternatively, translate by width after mFlip
    }

    g.translate(mOffsetX, mOffsetY);

    g.drawImage(mSheet.getSubImage(mCurrent), 0, 0);

    g.popTransform();
  }

  @Override
  public void update(GameTime time) {
    if (!mAnimator.isFinished() && mClock.needsSync(time.elapsedMilli)) {
      mClock.sync(time.elapsedMilli);

      mCurrent = mAnimator.next(mCurrent);
    }
  }
}
