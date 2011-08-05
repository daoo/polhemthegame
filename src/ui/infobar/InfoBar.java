/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package ui.infobar;

// TODO: A 1px high bar turns weird color-wise from time to time (probably because of aa)

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import components.interfaces.ICompRender;

public class InfoBar implements ICompRender {
  private final float          barWidth, barHeight;
  private final int            offsetX, offsetY;
  private final ArrayList<Bar> bars;

  public InfoBar(final float width, final float height,
                 final int offsetX, final int offsetY) {
    barWidth = width;
    barHeight = height;

    this.offsetX = offsetX;
    this.offsetY = offsetY;

    bars = new ArrayList<Bar>();
  }

  @Override
  public void render(final Graphics g) {
    int i = 0;
    for (final Bar b : bars) {
      b.render(g, offsetX, offsetY + (barHeight * i), barWidth, barHeight);
      ++i;
    }
  }

  public void add(final Bar bar) {
    bars.add(bar);
  }

  public float getWidth() {
    return barWidth;
  }
}
