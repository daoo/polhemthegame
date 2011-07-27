/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package components.graphics;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import other.GameTime;

import basics.Clock;

import components.interfaces.ICompAnim;

public class RSheet implements ICompAnim {
  protected final Clock       clock;

  protected final SpriteSheet sheet;
  protected final int         horizontalTileCount, verticalTileCount, tw, th;
  protected final int         offsetX, offsetY;
  protected int               sx, sy;
  protected boolean           running;

  public RSheet(final float targetFrameRate, final int offsetX,
                final int offsetY, final SpriteSheet sheet) {
    this.sheet = sheet;
    clock = new Clock(1.0f / targetFrameRate);
    running = false;

    this.offsetX = offsetX;
    this.offsetY = offsetY;

    // TODO: There should be getters in SpriteSheet 
    tw = sheet.getSubImage(0, 0).getWidth();
    th = sheet.getSubImage(0, 0).getHeight();

    horizontalTileCount = sheet.getHorizontalCount();
    verticalTileCount = sheet.getVerticalCount();

    sx = 0;
    sy = 0;
  }

  @Override
  public void start() {
    running = true;
  }

  @Override
  public void stop() {
    running = false;
  }

  @Override
  public void restart() {
    goToFirstFrame();
    start();
  }

  @Override
  public void update(final GameTime time) {
    if (running && clock.needsSync(time.getElapsed())) {
      clock.sync(time.getElapsed());

      sx++;
      if (sx >= horizontalTileCount) {
        sx = 0;
        sy++;

        if (sy >= verticalTileCount) {
          sy = 0;
        }
      }
    }
  }

  @Override
  public void render(final Graphics g) {
    g.drawImage(sheet.getSubImage(sx, sy), offsetX, offsetY);
  }

  @Override
  public void goToFirstFrame() {
    sx = 0;
    sy = 0;
  }

  @Override
  public boolean isRunning() {
    return running;
  }

  public SpriteSheet getSpriteSheet() {
    return sheet;
  }

  public Image getCurrentFrame() {
    return sheet.getSubImage(sx, sy);
  }

  @Override
  public Image getLastFrame() {
    return sheet.getSubImage(horizontalTileCount - 1, verticalTileCount - 1);
  }

  public int getTileWidth() {
    return tw;
  }

  public int getTileHeight() {
    return th;
  }
}
