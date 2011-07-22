/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package components.graphics;

import org.newdawn.slick.SpriteSheet;

import other.GameTime;

public class RSheetOnce extends RSheet {
  public RSheetOnce(final float targetFrameRate, final int offsetX,
                    final int offsetY, final SpriteSheet sheet) {
    super(targetFrameRate, offsetX, offsetY, sheet);
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
          // Stop on the last frame
          sx = horizontalTileCount - 1;
          sy--;
          running = false;
        }
      }
    }
  }
}
