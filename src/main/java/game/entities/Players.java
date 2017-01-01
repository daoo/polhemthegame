/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.entities;

import java.util.List;

import math.Aabb;
import math.Vector2;

public class Players {
  private static final float STARING_X = 0.1f;

  /**
   * Reposition players in the rectangle.
   * Places the players evenly spaced on the y axis with 10% of the width
   * as margin on the left side of the players.
   *
   * @param players the players to reposition
   * @param box the rectangle to use for alignment
   */
  public static void reposition(List<EntityImpl> players, Aabb box) {
    float dx = 0;
    float dy = box.getSize().y / (players.size() * 2.0f);

    float x = box.getMin().x + box.getSize().x * STARING_X;
    float y = box.getMin().y + dy;

    for (EntityImpl p : players) {
      p.getBody().setPosition(new Vector2(x, y - p.getBody().getSize().y / 2.0f));

      x += dx;
      y += dy;
    }
  }
}
