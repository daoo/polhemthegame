/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics;


import game.components.ComponentMessages;
import game.components.graphics.animations.IAnimator;
import game.components.interfaces.ICompAnim;
import math.time.Clock;
import math.time.GameTime;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;


public class RSheet implements ICompAnim {
  private final Clock       clock;

  private final SpriteSheet sheet;
  private final Tile        size;
  private final int         tw, th;
  private final int         offsetX, offsetY;
  private Tile              current;
  private IAnimator         animator;

  public RSheet(final float targetFrameRate, final int offsetX,
                final int offsetY, final SpriteSheet sheet,
                final IAnimator animator) {
    this.sheet = sheet;
    clock = new Clock(1.0f / targetFrameRate);

    this.offsetX = offsetX;
    this.offsetY = offsetY;

    // TODO: There should be getters in SpriteSheet
    tw = sheet.getSubImage(0, 0).getWidth();
    th = sheet.getSubImage(0, 0).getHeight();

    size = new Tile(sheet.getHorizontalCount(), sheet.getVerticalCount());

    current = Tile.ZERO;

    this.animator = animator;
  }

  @Override
  public void update(final GameTime time) {
    if (clock.needsSync(time.getElapsed())) {
      clock.sync(time.getElapsed());

      current = animator.next(current);
    }
  }

  @Override
  public void render(final Graphics g) {
    g.drawImage(sheet.getSubImage(current.x, current.y), offsetX, offsetY);
  }

  @Override
  public void goToFirstFrame() {
    current = Tile.ZERO;
  }

  @Override
  public void setAnimator(final IAnimator animator) {
    this.animator = animator;
  }

  public SpriteSheet getSpriteSheet() {
    return sheet;
  }

  public Image getCurrentFrame() {
    return sheet.getSubImage(current.x, current.y);
  }

  @Override
  public int getTileWidth() {
    return tw;
  }

  @Override
  public int getTileHeight() {
    return th;
  }

  @Override
  public Tile getTileCount() {
    return size;
  }

  @Override
  public IAnimator getAnimator() {
    return animator;
  }

  @Override
  public Tile getLastTile() {
    return new Tile(size.x - 1, size.y - 1);
  }

  @Override
  public void reciveMessage(final ComponentMessages message) {
    // Do nothing
  }
}
