package game;

import java.util.LinkedList;

public class InspectorData {
  private static final String EMPTY_STRING = "";

  private class Entry {
    public final String key;
    public final Object value;

    public Entry(String key, Object value) {
      this.key = key;
      this.value = value;
    }

    @Override
    public String toString() {
      return key + " = " + value.toString();
    }
  }

  private final LinkedList<Entry> data;

  public InspectorData() {
    data = new LinkedList<>();
  }

  public void add(String name, Object obj) {
    data.add(new Entry(name, obj));
  }

  public String toStringIndented(String in) {
    StringBuilder builder = new StringBuilder();
    for (Entry e : data) {
      builder.append(in);
      builder.append(e.toString());
      builder.append("\n");
    }

    return builder.toString();
  }

  @Override
  public String toString() {
    return toStringIndented(EMPTY_STRING);
  }
}
