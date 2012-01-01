/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package util;


public class Tree<T> {
  public final Node<T> root;

  public Tree(Node<T> root) {
    assert root != null;

    this.root = root;
  }

  private static final String INDENTATION = "  ";

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    toStringHelper(sb, "", root);

    return sb.toString();
  }

  private void toStringHelper(StringBuilder sb, String in, Node<T> node) {
    sb.append(in);
    sb.append(node.value.toString());
    sb.append("\n");
    for (Node<T> child : node.nodes) {
      toStringHelper(sb, in + INDENTATION, child);
    }
  }
}
