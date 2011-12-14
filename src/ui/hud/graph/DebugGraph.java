package ui.hud.graph;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class DebugGraph {
  /**
   * Time difference between debug info measures in milliseconds.
   */
  private static final long MEASURE_TIME_DELTA = 100;

  private int width, height;

  private final GraphData updateData, renderData;

  private long updateStartTime;

  public DebugGraph(int width, int height) {
    this.width = width;
    this.height = height;

    updateData = new GraphData(width);
    renderData = new GraphData(width);
  }

  public void render(Graphics g, int x, int y) {
    g.pushTransform();
    g.translate(x, y);

    float updateAvg = updateData.getAverage();

    g.setColor(new Color(0, 255, 0, 200));

    int px = 0;
    for (Float data : updateData) {
      float dataHeight = data.floatValue() / updateAvg;
      float pixelHeight = dataHeight * height;

      g.drawLine(px, height, px, -pixelHeight);

      ++px;
    }

    g.popTransform();
  }

  public void startUpdateMeasure() {
    updateStartTime = System.nanoTime();
  }

  public void stopUpdateMeasure() {
    long delta = System.nanoTime() - updateStartTime;

    updateData.addDataPoint(delta / 10000000.0f); // milliseconds
  }
}
