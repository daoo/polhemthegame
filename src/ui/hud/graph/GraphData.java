package ui.hud.graph;

import java.util.Iterator;
import java.util.LinkedList;

public class GraphData implements Iterable<Float> {
  private final int dataCount;

  private LinkedList<Float> data;

  private float average;

  public GraphData(int dataCount) {
    this.dataCount = dataCount;
    data = new LinkedList<>();
  }

  public void addDataPoint(float dataPoint) {
    if (data.size() >= dataCount) {
      data.removeFirst();
    }

    data.add(Float.valueOf(dataPoint));

    average = (average + dataPoint) / 2.0f;
  }

  public float getAverage() {
    return average;
  }

  @Override
  public Iterator<Float> iterator() {
    return data.iterator();
  }
}
