/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package util;

/**
 * Simple Rose Tree data structure.
 * @param <T> type held by the nodes
 */
public class Tree<T> {
  private static final String EMPTY_STRING = "";
  private static final String NEW_LINE = "\n";
  private static final String INDENTATION = "  ";

  /**
   * The root node.
   */
  public final Node<T> root;

  /**
   * Create a new tree with the specific root node.
   * @param root the root node to use in the tree
   */
  public Tree(Node<T> root) {
    assert root != null;

    this.root = root;
  }

  /**
   * Creates an nicely indent string of the entire tree.
   * @return a string resembling the tree
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    toStringHelper(sb, EMPTY_STRING, root);

    return sb.toString();
  }

  /**
   * Recursive method for appending a tree to a string builder.
   * @param sb the string builder to append to
   * @param in current indentation string
   * @param node the node to recurse over
   */
  private static <T> void toStringHelper(StringBuilder sb, String in, Node<T> node) {
    sb.append(in);
    sb.append(node.value);
    sb.append(NEW_LINE);
    for (Node<T> child : node.nodes) {
      toStringHelper(sb, in + INDENTATION, child);
    }
  }
}
