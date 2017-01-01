package game.components.ai;

import org.junit.Test;

import math.Aabb;
import math.Circle;
import math.Rectangle;
import math.Vector2;
import util.Random;

import static org.junit.Assert.assertTrue;

public class WalkingTest {
  @Test
  public void testTargetCalculationInvariants() {
    Random rnd = new Random();

    Vector2 min = new Vector2(rnd.nextInt(1000), rnd.nextInt(1000));
    Vector2 max = new Vector2(rnd.nextInt(1100, 2000), rnd.nextInt(1100, 2000));
    Aabb box = new Aabb(min, Rectangle.fromExtents(min, max));

    Vector2 center = new Vector2(rnd.nextInt(2000), rnd.nextInt(2000));
    int radius = rnd.nextInt(50, 500);
    Circle circle = new Circle(center, radius);

    for (int i = 0; i < 9999999; ++i) {
      Vector2 p = Walking.newRandomTarget(rnd, box, circle);
      assertTrue(box.contains(p));
      assertTrue(!circle.contains(p));
    }
  }
}