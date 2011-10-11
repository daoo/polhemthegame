/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package ui.hud.infobar;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Bar {
  private final Color colorHave;
  private final Color colorLost;

  private final IProgress progress;
  private float fraction;

  public Bar(final IProgress progress, final Color colorHave, final Color colorLost) {
    assert (progress != null);
    assert (colorHave != null);
    assert (colorLost != null);

    this.progress = progress;
    fraction = 1.0f;

    this.colorHave = colorHave;
    this.colorLost = colorLost;
  }

  public void update() {
    fraction = progress.getProgress();
  }

  public void render(final Graphics g, final float x, final float y,
                     final float width, final float height) {
    g.setColor(colorHave);
    g.fillRect(x, y, fraction * width, height);

    g.setColor(colorLost);
    g.fillRect(x + (fraction * width), y, (1.0f - fraction) * width, height);
  }
}
