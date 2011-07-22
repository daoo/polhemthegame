/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package main;

import basics.Rectangle;
import entities.Player;
import entities.Players;

public class WorldFactory {
  public static World make(final Rectangle rect, final Players players) {
    final World w = new World(rect.getWidth(), rect.getHeight());
    for (final Player p : players) {
      w.add(p);
    }

    return w;
  }
}
