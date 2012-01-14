/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package tests;

import static org.junit.Assert.assertEquals;
import math.Random;
import math.Vector2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Vector2Test {
  private static int RANGE = 100;

  private Random random;

  private Vector2 unitRight;

  private Vector2 randomVector2() {
    return new Vector2(
      random.nextInt(-RANGE, RANGE), random.nextInt(-RANGE, RANGE));
  }

  @Before
  public void setUp() throws Exception {
    random = new Random();

    unitRight = new Vector2(1, 0);
  }

  @After
  public void tearDown() throws Exception {
    // Do nothing
  }

  @Test
  public final void testMagnitude() {
    assertEquals(1, unitRight.magnitude(), 0.1f);
  }

  @Test
  public final void testMagnitudeSquared() {
    assertEquals(1, unitRight.magnitudeSquared(), 0.1f);

    for (int i = 0; i < 100; ++i) {
      int a = random.nextInt(-RANGE, RANGE);
      Vector2 v = randomVector2();

      assertEquals(
        v.magnitudeSquared(),
        Vector2.dot(v, v),
        0.1f);

      assertEquals(
        Vector2.multiply(v, a).magnitude(),
        Math.abs(a) * v.magnitude(),
        0.1f);
    }
  }

  @Test
  public final void testNormalize() {
    assertEquals(unitRight, unitRight.normalize());

    for (int i = 0; i < 100; ++i) {
      assertEquals(1, randomVector2().normalize().magnitude(), 0.001f);
    }
  }

  @Test
  public final void testDot() {
    for (int i = 0; i < 100; ++i) {
      int a = random.nextInt(-RANGE, RANGE);
      Vector2 v = randomVector2();
      Vector2 u = randomVector2();
      Vector2 w = randomVector2();

      assertEquals(
        Vector2.dot(Vector2.add(u, v), w),
        Vector2.dot(u, w) + Vector2.dot(v, w),
        0.1f);

      assertEquals(
        Vector2.dot(Vector2.multiply(u, a), v),
        a * Vector2.dot(v, u),
        0.1f);

      assertEquals(
        Vector2.dot(v, u),
        Vector2.dot(u, v),
        0.1f);

      assertEquals(
        Vector2.dot(u, u),
        u.magnitudeSquared(),
        0.1f);

      assertEquals(
        Vector2.dot(u, u.normalize()),
        u.magnitude(),
        0.1f);
    }
  }

  @Test
  public final void testDistanceSubtract() {
    for (int i = 0; i < 100; ++i) {
      Vector2 v = randomVector2();
      Vector2 u = randomVector2();

      assertEquals(
        Vector2.subtract(v, u).magnitude(),
        Vector2.distance(u, v),
        0.1f);
    }
  }
}
