package util;

import java.util.List;

public class DebugHelper {
  public static Node<Object> listToNode(String title, List<? extends Object> lst) {
    Node<Object> logic = new Node<Object>(String.format("%s (%d)", title, lst.size()));
    for (Object obj : lst) {
      logic.nodes.add(new Node<>(obj));
    }

    return logic;
  }
}
