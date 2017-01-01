/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import game.components.IRenderComponent;
import game.types.GameTime;
import game.types.Message;
import game.types.Orientation;

public class TexturedQuad implements IRenderComponent {
  private final int mCenterX;
  private final int mCenterY;
  private final boolean mFlip;
  private final int mAngle;
  private final Image mImg;

  public TexturedQuad(Image img, Orientation orientation, int angle) {
    mImg = img;
    mFlip = orientation == Orientation.LEFT;
    mAngle = angle;

    mCenterX = img.getWidth() / 2;
    mCenterY = img.getHeight() / 2;
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    // Do nothing
  }

  @Override
  public void render(Graphics g) {
    g.pushTransform();
    if (mFlip) {
      // Be sure to flip around the center
      g.translate(mCenterX, 0);
      g.scale(-1, 1);
      g.translate(-mCenterX, 0);
      // Alternatively, translate by width after flip
    }
    g.rotate(mCenterX, mCenterY, mAngle);

    g.drawImage(mImg, 0, 0);
    g.popTransform();
  }

  @Override
  public void update(GameTime time) {
    // Do nothing
  }
}
