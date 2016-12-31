/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package tests;

import org.junit.Test;

import math.Vector2;
import util.Random;

import static org.junit.Assert.assertEquals;

public class Vector2Test {
  private static final int RANGE = 100;

  private final Random mRandom = new Random();
  private final Vector2 mUnitRight = new Vector2(1, 0);

  private Vector2 randomVector2() {
    return new Vector2(mRandom.nextInt(-RANGE, RANGE), mRandom.nextInt(-RANGE, RANGE));
  }

  @Test
  public final void testMagnitude() {
    assertEquals(1, mUnitRight.magnitude(), 0.1f);
  }

  @Test
  public final void testMagnitudeSquared() {
    assertEquals(1, mUnitRight.magnitudeSquared(), 0.1f);

    for (int i = 0; i < 100; ++i) {
      int a = mRandom.nextInt(-RANGE, RANGE);
      Vector2 v = randomVector2();

      assertEquals(v.magnitudeSquared(), Vector2.dot(v, v), 0.1f);

      assertEquals(Vector2.multiply(v, a).magnitude(), Math.abs(a) * v.magnitude(), 0.1f);
    }
  }

  @Test
  public final void testNormalize() {
    assertEquals(mUnitRight, mUnitRight.normalize());

    for (int i = 0; i < 100; ++i) {
      assertEquals(1, randomVector2().normalize().magnitude(), 0.001f);
    }
  }

  @Test
  public final void testDot() {
    for (int i = 0; i < 100; ++i) {
      int a = mRandom.nextInt(-RANGE, RANGE);
      Vector2 v = randomVector2();
      Vector2 u = randomVector2();
      Vector2 w = randomVector2();

      assertEquals(Vector2.dot(Vector2.add(u, v), w), Vector2.dot(u, w) + Vector2.dot(v, w), 0.1f);

      assertEquals(Vector2.dot(Vector2.multiply(u, a), v), a * Vector2.dot(v, u), 0.1f);

      assertEquals(Vector2.dot(v, u), Vector2.dot(u, v), 0.1f);

      assertEquals(Vector2.dot(u, u), u.magnitudeSquared(), 0.1f);

      assertEquals(Vector2.dot(u, u.normalize()), u.magnitude(), 0.1f);
    }
  }

  @Test
  public final void testDistanceSubtract() {
    for (int i = 0; i < 100; ++i) {
      Vector2 v = randomVector2();
      Vector2 u = randomVector2();

      assertEquals(Vector2.subtract(v, u).magnitude(), Vector2.distance(u, v), 0.1f);
    }
  }
}