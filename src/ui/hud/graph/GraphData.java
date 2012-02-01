/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package ui.hud.graph;

import java.util.Iterator;

import util.CircularOverwriteArray;

public class GraphData implements Iterable<Double> {
  private CircularOverwriteArray<Double> data;

  private double average;

  public GraphData(int dataCount) {
    data = new CircularOverwriteArray<>(dataCount, 0.0);
  }

  public void addDataPoint(double dataPoint) {
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
