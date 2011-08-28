/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package com.daoo.components.graphics;


import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import com.daoo.components.ComponentMessages;
import com.daoo.components.graphics.animations.IAnimator;
import com.daoo.components.interfaces.ICompAnim;
import com.daoo.math.time.Clock;
import com.daoo.math.time.GameTime;

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
  public Image getLastFrame() {
    return sheet.getSubImage(size.x - 1, size.y - 1);
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
  public void reciveMessage(ComponentMessages message) {
    // Do nothing
  }
}
