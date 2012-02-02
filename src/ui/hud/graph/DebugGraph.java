/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package ui.hud.graph;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import util.CircularOverwriteArray;

@SuppressWarnings("boxing")
public class DebugGraph {
  /**
   * Time difference between debug info measures in nanoseconds.
   */
  private static final long MEASURE_TIME_DELTA = 10000000;

  private static final long NANO_PER_MILLI = 1000000;

  private static final double MILLISECONDS_PER_FRAME = 5;

  private int width, height;

  private final Measure updateMeasure, renderMeasure;
  private final CircularOverwriteArray<Double> updateData, renderData;

  public DebugGraph(int width, int height) {
    this.width = width;
    this.height = height;

    updateData = new CircularOverwriteArray<>(width, 0.0);
    renderData = new CircularOverwriteArray<>(width, 0.0);

    updateMeasure = new Measure(MEASURE_TIME_DELTA);
    renderMeasure = new Measure(MEASURE_TIME_DELTA);
  }

  public void render(Graphics g, int x, int y) {
    g.pushTransform();
    g.translate(x, y);

    // Render render
    g.setColor(new Color(0, 0, 255, 200));
    drawData(renderData, g);

    // Render update
    g.setColor(new Color(0, 255, 0, 200));
    drawData(updateData, g);

    g.setColor(Color.white);
    g.drawRect(0, 0, width, height);
    g.popTransform();
  }

  public void startRenderMeasure() {
    renderMeasure.startMeasure();
  }

  public void stopRenderMeasure() {
    renderMeasure.stopMeasure();
    if (renderMeasure.isFinished()) {
      renderData.add(renderMeasure.getAverage() / (double) NANO_PER_MILLI);
      renderMeasure.reset();
    }
  }

  public void startUpdateMeasure() {
    updateMeasure.startMeasure();
  }

  public void stopUpdateMeasure() {
    updateMeasure.stopMeasure();

    if (updateMeasure.isFinished()) {
      updateData.add(updateMeasure.getAverage() / (double) NANO_PER_MILLI);
      updateMeasure.reset();
    }
  }

  private void drawData(Iterable<Double> graphData, Graphics g) {
    int px = 0;
    for (Double data : graphData) {
      double dataHeight = data.doubleValue() / (MILLISECONDS_PER_FRAME);
      int pixelHeight = (int) (dataHeight * height);

      g.drawLine(px, height - pixelHeight, px, height);

      ++px;
    }
  }
}
