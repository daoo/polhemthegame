/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package ui.infobar;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Bar {
  protected Color colorHave;
  protected Color colorLost;

  protected float fraction;

  public Bar(final Color colorHave, final Color colorLost) {
    assert (colorHave != null);
    assert (colorLost != null);

    fraction = 1.0f;

    this.colorHave = colorHave;
    this.colorLost = colorLost;
  }

  public void setFraction(final float f) {
    fraction = f;
  }

  public void render(final Graphics g, final float x, final float y,
                     final float width, final float height) {
    g.setColor(colorHave);
    g.fillRect(x, y, fraction * width, height);

    g.setColor(colorLost);
    g.fillRect(x + (fraction * width), y, (1.0f - fraction) * width, height);
  }
}
