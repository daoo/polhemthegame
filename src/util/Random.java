/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package util;

public class Random {
  private final java.util.Random random;

  public Random() {
    random = new java.util.Random();
  }

  public boolean nextBool() {
    return random.nextBoolean();
  }

  public int nextInt() {
    return random.nextInt();
  }

  public int nextInt(int max) {
    assert max > 0;

    return random.nextInt(max);
  }

  public int nextInt(int min, int max) {
    assert min < max;

    return min + random.nextInt(max - min);
  }

  public float nextFloat() {
    return random.nextFloat();
  }

  public float nextFloat(float max) {
    assert max > 0;

    return random.nextFloat() * max;
  }

  public float nextFloat(float min, float max) {
    assert min < max;

    return min + random.nextFloat() * (max - min);
  }
}
