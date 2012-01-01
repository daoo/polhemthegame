/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package ui.hud.graph;

import java.util.Iterator;
import java.util.LinkedList;

public class GraphData implements Iterable<Double> {
  private final int dataCount;

  private LinkedList<Double> data;

  private double average;

  public GraphData(int dataCount) {
    this.dataCount = dataCount;
    data = new LinkedList<>();
  }

  public void addDataPoint(double dataPoint) {
    if (data.size() >= dataCount) {
      data.removeFirst();
    }

    data.add(Double.valueOf(dataPoint));

    average = (average + dataPoint) / 2.0;
  }

  public double getAverage() {
    return average;
  }

  @Override
  public Iterator<Double> iterator() {
    return data.iterator();
  }
}
