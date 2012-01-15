/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics;

import game.components.Message;
import game.components.graphics.animations.Continuous;
import game.components.graphics.animations.IAnimator;
import game.components.graphics.animations.Idle;
import game.components.graphics.animations.Tile;
import game.components.interfaces.IAnimatedComponent;
import game.misc.Clock;
import game.pods.GameTime;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import util.SpriteSheet;

public class RSheet implements IAnimatedComponent {
  private static final IAnimator IDLE = new Idle();

  private IAnimator animator;

  private final Clock clock;
  private Tile current;
  private final int offsetX, offsetY;

  private final SpriteSheet sheet;
  private final Tile size;

  public RSheet(float targetFrameRate, int offsetX, int offsetY, SpriteSheet sheet) {
    this.sheet = sheet;

    this.offsetX = offsetX;
    this.offsetY = offsetY;

    size = new Tile(sheet.getTileCountX(), sheet.getTileCountY());

    current = Tile.ZERO;
    animator = IDLE;

    clock = new Clock(1.0f / targetFrameRate);
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
    g.drawImage(sheet.getSubImage(current.x, current.y), offsetX, offsetY);
  }

  @Override
  public void setAnimator(IAnimator animator) {
    this.animator = animator;
  }

  @Override
  public void update(GameTime time) {
    if (!animator.isFinished() && clock.needsSync(time.elapsed)) {
      clock.sync(time.elapsed);

      current = animator.next(current);
    }
  }
}
