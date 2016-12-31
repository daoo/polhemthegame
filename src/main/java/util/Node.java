/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package util;

import java.util.ArrayList;
import java.util.List;

/**
 * Node for a Rose Tree.
 *
 * @param <T> the type held by the node
 */
@SuppressWarnings("ALL")
public class Node<T> {
  public final T value;
  public final List<Node<T>> nodes;

  /**
   * Construct a new node with the specific value
   *
   * @param value the value to hold
   */
  public Node(T value) {
    this.value = value;

    nodes = new ArrayList<>(5);
  }

  /**
   * Calls value.toString().
   *
   * @return value.toString()
   */
  @Override
  public String toString() {
    return value.toString();
  }
}
