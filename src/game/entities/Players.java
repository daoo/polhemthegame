/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.entities;

import java.util.List;

import math.Rectangle;
import math.Vector2;

public class Players {
  private static final float STARING_X = 0.1f;

  /**
   * Reposition players in the rectangle.
   * Places the players evenly spaced on the y axis with 10% of the width
   * as margin on the left side of the players.
   * @param players the players to reposition
   * @param rect the rectangle to use for alignment
   */
  public static void reposition(List<? extends IEntity> players, Rectangle rect) {
    float dx = 0;
    float dy = rect.getHeight() / (players.size() * 2);

    float x = rect.getX1() + rect.getWidth() * Players.STARING_X;
    float y = rect.getY1() + dy;

    for (IEntity p : players) {
      p.getBody().setPosition(new Vector2(x, y - p.getBody().getHeight() / 2.0f));

      x += dx;
      y += dy;
    }
  }
}
