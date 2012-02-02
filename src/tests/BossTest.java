package tests;

import game.components.ai.Walking;
import junit.framework.Assert;
import math.ExMath;
import math.IRandom;
import math.Random;
import math.Rectangle;
import math.Vector2;

import org.junit.Test;

public class BossTest {
  @Test
  public void test() {
    IRandom rnd = new Random();
    Rectangle rect = randomRect(rnd);
    Vector2 cPos = new Vector2(rnd.nextInt(2000), rnd.nextInt(2000));
    float radius = rnd.nextInt(50, 500);
    float radiusSquared = ExMath.square(radius);

    for (int i = 0; i < 99999; ++i) {
      Vector2 target = Walking.newRandomTarget(rnd, rect, cPos, radiusSquared);

      Assert.assertTrue(String.format("Not within rectangle, rect: %s, target: %s",
          rect, target),
          Rectangle.contains(rect, target));
      Assert.assertTrue(String.format("Not outside of circle, distance: %f",
          Vector2.distance(target, cPos) - radius),
          Vector2.distanceSquared(target, cPos) >= radiusSquared);
    }
  }

  private static Rectangle randomRect(IRandom rnd) {
    return new Rectangle(rnd.nextInt(1000), rnd.nextInt(1000),
        rnd.nextInt(100, 1000), rnd.nextInt(100, 1000));
  }
}
