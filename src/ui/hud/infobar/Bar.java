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

  public Bar(IProgress progress, Color colorHave, Color colorLost) {
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

  public void render(Graphics g, float x, float y, float width, float height) {
    float middle = fraction * width;

    g.setColor(colorHave);
    g.fillRect(x, y, middle, height);

    g.setColor(colorLost);
    g.fillRect(x + middle, y, width - middle, height);
  }
}
