package util;

import java.util.LinkedList;

public class Node<T> {
  public final T value;
  public final LinkedList<Node<T>> nodes;

  public Node(T value) {
    this.value = value;

    nodes = new LinkedList<>();
  }

  @Override
  public String toString() {
    return value.toString();
  }
}
