/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package debug;

import java.util.Collection;

import util.Node;

public class DebugHelper {
  public static Node<String> listToNode(String title, Collection<?> lst) {
    Node<String> node = new Node<>(title + " (" + lst.size() + ")");

    for (Object obj : lst) {
      if (obj instanceof Debuggable) {
        node.nodes.add(((Debuggable) obj).debugTree());
      } else {
        node.nodes.add(new Node<>(obj.toString()));
      }
    }

    return node;
  }
}
