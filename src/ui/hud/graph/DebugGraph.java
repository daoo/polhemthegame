package ui.hud.graph;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class DebugGraph {
  /**
   * Time difference between debug info measures in nanoseconds.
   */
  private static final long MEASURE_TIME_DELTA = 10000000;

  private static final long NANO_PER_MILLI = 1000000;

  private static final double MILLISECONDS_PER_FRAME = 5;

  private int width, height;

  private final Measure updateMeasure, renderMeasure;
  private final GraphData updateData, renderData;

  public DebugGraph(int width, int height) {
    this.width = width;
    this.height = height;

    updateData = new GraphData(width);
    renderData = new GraphData(width);

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
      renderData.addDataPoint(
        renderMeasure.getAverage() / (double) NANO_PER_MILLI);
      renderMeasure.reset();
    }
  }

  public void startUpdateMeasure() {
    updateMeasure.startMeasure();
  }

  public void stopUpdateMeasure() {
    updateMeasure.stopMeasure();

    if (updateMeasure.isFinished()) {
      updateData.addDataPoint(
        updateMeasure.getAverage() / (double) NANO_PER_MILLI); // milliseconds
      updateMeasure.reset();
    }
  }

  private void drawData(GraphData graphData, Graphics g) {
    int px = 0;
    for (Double data : graphData) {
      double dataHeight = data.doubleValue() / (MILLISECONDS_PER_FRAME);
      int pixelHeight = (int) (dataHeight * height);

      g.drawLine(px, height - pixelHeight, px, height);

      ++px;
    }
  }
}
