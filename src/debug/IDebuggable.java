/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package debug;

import util.Node;

public interface IDebuggable {
  /**
   * Returns a debug node. A debug node is a node with children that contains
   * information related to the game.
   * @return a debug node
   */
  public Node<String> debugTree();

  /**
   * A short descriptive string with debugging information.
   * @return a string
   */
  public String debugString();
}
