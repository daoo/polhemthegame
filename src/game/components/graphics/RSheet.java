/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.graphics.animations.Continuous;
import game.components.graphics.animations.IAnimator;
import game.components.graphics.animations.Idle;
import game.components.graphics.animations.RunTo;
import game.components.graphics.animations.Tile;
import game.components.interfaces.IAnimatedComponent;
import game.time.Clock;
import game.time.GameTime;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class RSheet implements IAnimatedComponent {
  private IAnimator   animator;

  private final Clock clock;
  private Tile        current;
  private final int  offsetX, offsetY;

  private final SpriteSheet sheet;
  private final Tile size;
  private final int  tw, th;

  public RSheet(float targetFrameRate, int offsetX, int offsetY,
                SpriteSheet sheet, IAnimator animator) {
    this.sheet = sheet;
    clock      = new Clock(1.0f / targetFrameRate);

    this.offsetX = offsetX;
    this.offsetY = offsetY;

    // Note: There should be getters in SpriteSheet
    tw = sheet.getSubImage(0, 0).getWidth();
    th = sheet.getSubImage(0, 0).getHeight();

    size = new Tile(sheet.getHorizontalCount(), sheet.getVerticalCount());

    current = Tile.ZERO;

    this.animator = animator;
  }

  @Override
  public IAnimator getAnimator() {
    return animator;
  }

  @Override
  public ComponentType getComponentType() {
    return ComponentType.GRAPHIC;
  }

  public Image getCurrentFrame() {
    return sheet.getSubImage(current.x, current.y);
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
    return th;
  }

  @Override
  public int getTileWidth() {
    return tw;
  }

  @Override
  public void goToFirstFrame() {
    current = Tile.ZERO;
  }

  @Override
  public void reciveMessage(ComponentMessage message, Object args) {
    if (message == ComponentMessage.START_ANIMATION) {
      animator = new Continuous(getTileCount());
    } else if (message == ComponentMessage.STOP_ANIMATION) {
      if (!animator.isFinished()) {
        goToFirstFrame();
        animator = new Idle();
      }
    } else if (message == ComponentMessage.END_ANIMATION) {
      animator = new RunTo(getTileCount(), getLastTile());
    }
  }

  @Override
  public void render(Graphics g) {
    g.drawImage(sheet.getSubImage(current.x, current.y), offsetX, offsetY);
  }

  @Override
  public void setAnimator(IAnimator animator) {
    this.animator = animator;
  }

  @Override
  public void update(GameTime time) {
    if (clock.needsSync(time.elapsed)) {
      clock.sync(time.elapsed);

      current = animator.next(current);
    }
  }
}
