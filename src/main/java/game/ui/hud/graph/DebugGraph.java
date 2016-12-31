/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.ui.hud.graph;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import util.CircularFloatArray;

public class DebugGraph {
  /**
   * Time difference between debug info measures in nanoseconds.
   */
  private static final long MEASURE_TIME_DELTA = 10000000;

  private static final long NANO_PER_MILLI = 1000000;

  private static final double MILLISECONDS_PER_FRAME = 5;

  private static final Color BLUE = new Color(0, 0, 255, 200);
  private static final Color GREEN = new Color(0, 255, 0, 200);

  private final int mWidth;
  private final int mHeight;

  private final Measure mUpdateMeasure;
  private final Measure mRenderMeasure;
  private final CircularFloatArray mUpdateData;
  private final CircularFloatArray mRenderData;

  public DebugGraph(int width, int height) {
    mWidth = width;
    mHeight = height;

    mUpdateData = new CircularFloatArray(width, 0);
    mRenderData = new CircularFloatArray(width, 0);

    mUpdateMeasure = new Measure(MEASURE_TIME_DELTA);
    mRenderMeasure = new Measure(MEASURE_TIME_DELTA);
  }

  public void render(Graphics g) {
    g.setColor(BLUE);
    drawData(mRenderData, g);
    g.setColor(GREEN);
    drawData(mUpdateData, g);
    g.setColor(Color.white);
    g.drawRect(0, 0, mWidth, mHeight);
  }

  public void startRenderMeasure() {
    mRenderMeasure.startMeasure();
  }

  public void stopRenderMeasure() {
    mRenderMeasure.stopMeasure();
    if (mRenderMeasure.isFinished()) {
      mRenderData.add((float) (mRenderMeasure.getAverage() / (double) NANO_PER_MILLI));
      mRenderMeasure.reset();
    }
  }

  public void startUpdateMeasure() {
    mUpdateMeasure.startMeasure();
  }

  public void stopUpdateMeasure() {
    mUpdateMeasure.stopMeasure();

    if (mUpdateMeasure.isFinished()) {
      mUpdateData.add((float) (mUpdateMeasure.getAverage() / (double) NANO_PER_MILLI));
      mUpdateMeasure.reset();
    }
  }

  private void drawData(CircularFloatArray graphData, Graphics g) {
    int px = 0;
    CircularFloatArray.FloatIterator it = graphData.iterator();
    while (it.hasNext()) {
      double data = it.next();
      double dataHeight = data / MILLISECONDS_PER_FRAME;
      int pixelHeight = (int) (dataHeight * mHeight);

      g.drawLine(px, mHeight - pixelHeight, px, mHeight);

      ++px;
    }
  }
}
