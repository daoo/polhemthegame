/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package components.graphics;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import other.GameTime;
import basics.Clock;

import components.graphics.animations.IAnimator;
import components.interfaces.ICompAnim;

public class RSheet implements ICompAnim {
  private final Clock       clock;

  private final SpriteSheet sheet;
  private final Tile        size;
  private final int         horizontalTileCount, verticalTileCount, tw, th;
  private final int         offsetX, offsetY;
  private Tile              tile;
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
    horizontalTileCount = sheet.getHorizontalCount();
    verticalTileCount = sheet.getVerticalCount();

    tile = Tile.ZERO;

    this.animator = animator;
  }

  @Override
  public void update(final GameTime time) {
    if (clock.needsSync(time.getElapsed())) {
      clock.sync(time.getElapsed());

      tile = animator.next(tile);
    }
  }

  @Override
  public void render(final Graphics g) {
    g.drawImage(sheet.getSubImage(tile.x, tile.y), offsetX, offsetY);
  }

  @Override
  public void goToFirstFrame() {
    tile = Tile.ZERO;
  }

  @Override
  public void setAnimator(final IAnimator animator) {
    this.animator = animator;
  }

  public SpriteSheet getSpriteSheet() {
    return sheet;
  }

  public Image getCurrentFrame() {
    return sheet.getSubImage(tile.x, tile.y);
  }

  @Override
  public Image getLastFrame() {
    return sheet.getSubImage(horizontalTileCount - 1, verticalTileCount - 1);
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
}
