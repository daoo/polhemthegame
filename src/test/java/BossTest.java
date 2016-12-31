/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package tests;

import org.junit.Test;

import game.components.ai.Walking;
import math.ExtraMath;
import math.Rectangle;
import math.Vector2;
import util.Random;

import static org.junit.Assert.assertTrue;

public class BossTest {
  @Test
  public void test() {
    Random rnd = new Random();

    int rx1 = rnd.nextInt(1000);
    int ry1 = rnd.nextInt(1000);
    int rx2 = rnd.nextInt(1100, 2000);
    int ry2 = rnd.nextInt(1100, 2000);

    int cx = rnd.nextInt(2000);
    int cy = rnd.nextInt(2000);
    int radius = rnd.nextInt(50, 500);
    int radiusSquared = ExtraMath.square(radius);

    Rectangle rect = new Rectangle(rx1, ry1, rx2 - rx1, ry2 - ry1);
    Vector2 cPos = new Vector2(cx, cy);

    for (int i = 0; i < 9999999; ++i) {
      Vector2 p = Walking.newRandomTarget(rnd, rx1, ry1, rx2, ry2, cx, cy, radiusSquared);

      assertTrue(Rectangle.contains(rect, p));
      assertTrue(Vector2.distanceSquared(cPos, p) >= radiusSquared);
    }
  }
}