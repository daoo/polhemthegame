/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game;

import java.util.ArrayList;

public class InspectorData {
  private static final String EMPTY_STRING = "";
  private static final String NEW_LINE = "\n";
  private static final String EQUALS = " = ";

  private class Entry {
    public final String key;
    public final Object value;

    public Entry(String key, Object value) {
      this.key = key;
      this.value = value;
    }

    @Override
    public String toString() {
      return key + EQUALS + value.toString();
    }
  }

  private final ArrayList<Entry> data;

  public InspectorData() {
    data = new ArrayList<>();
  }

  public void add(String name, Object obj) {
    data.add(new Entry(name, obj));
  }

  public String toStringIndented(String in) {
    StringBuilder builder = new StringBuilder();
    for (Entry e : data) {
      builder.append(in);
      builder.append(e.toString());
      builder.append(NEW_LINE);
    }

    return builder.toString();
  }

  @Override
  public String toString() {
    return toStringIndented(EMPTY_STRING);
  }
}
