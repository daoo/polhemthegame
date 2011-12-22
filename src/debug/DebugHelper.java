package debug;

import java.util.List;

import util.Node;

public class DebugHelper {
  public static Node<String> listToNode(String title, List<? extends Object> lst) {
    Node<String> logic = new Node<>(String.format("%s (%d)", title, lst.size()));
    for (Object obj : lst) {
      logic.nodes.add(new Node<>(obj.toString()));
    }

    return logic;
  }
}
