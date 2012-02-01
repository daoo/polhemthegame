/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package util;

import java.util.ArrayList;

public class Node<T> {
  public final T value;
  public final ArrayList<Node<T>> nodes;

  public Node(T value) {
    this.value = value;

    nodes = new ArrayList<>();
  }

  @Override
  public String toString() {
    return value.toString();
  }
}
