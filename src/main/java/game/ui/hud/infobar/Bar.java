/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.ui.hud.infobar;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 * Bar for the InfoBar.
 */
public class Bar {
  private final Color mColorHave;
  private final Color mColorLost;

  private final IProgress mProgress;
  private float mFraction;

  /**
   * Construct a new bar backed by a specific progress and rendered using
   * specific colors. The progress specifies a value, a. The bar is rendered
   * in two parts: [0, a] is rendered using colorHave, [a, 1] is rendered using
   * colorLost.
   *
   * @param progress the progress to use
   * @param colorHave the color for the left part of the bar
   * @param colorLost the color for the right part of the bar
   */
  public Bar(IProgress progress, Color colorHave, Color colorLost) {
    assert progress != null;
    assert colorHave != null;
    assert colorLost != null;

    mProgress = progress;
    mFraction = progress.getProgress();

    mColorHave = colorHave;
    mColorLost = colorLost;
  }

  /**
   * Update the progress.
   */
  public void update() {
    mFraction = mProgress.getProgress();
  }

  /**
   * Render the bar to a specific graphics context at specific position with a
   * specific size.
   *
   * @param g the graphics context to render to
   * @param x the x coordinate
   * @param y the y coordinate
   * @param width the width of the bar
   * @param height the height of the bar
   */
  public void render(Graphics g, int x, int y, int width, int height) {
    int middle = (int) (mFraction * width);

    g.setColor(mColorHave);
    g.fillRect(x, y, middle, height);

    g.setColor(mColorLost);
    g.fillRect(x + middle, y, width - middle, height);
  }
}
