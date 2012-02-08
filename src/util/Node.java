/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package util;

import java.util.ArrayList;

/**
 * Node for a Rose Tree.
 * @param <T> the type held by the node
 */
public class Node<T> {
  public final T value;
  public final ArrayList<Node<T>> nodes;

  /**
   * Construct a new node with the specific value
   * @param value the value to hold
   */
  public Node(T value) {
    this.value = value;

    nodes = new ArrayList<>();
  }

  /**
   * Calls value.toString().
   * @return value.toString()
   */
  @Override
  public String toString() {
    return value.toString();
  }
}
