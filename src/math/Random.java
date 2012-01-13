/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package math;

public class Random implements IRandom {
  private final java.util.Random random;

  public Random() {
    random = new java.util.Random();
  }

  @Override
  public boolean nextBool() {
    return random.nextBoolean();
  }

  @Override
  public int nextInt() {
    return random.nextInt();
  }

  @Override
  public int nextInt(int max) {
    assert max > 0;

    return random.nextInt(max);
  }

  @Override
  public int nextInt(int min, int max) {
    assert min < max;

    return min + random.nextInt(max - min);
  }

  @Override
  public float nextFloat() {
    return random.nextFloat();
  }

  @Override
  public float nextFloat(float max) {
    assert max > 0;

    return random.nextFloat() * max;
  }

  @Override
  public float nextFloat(float min, float max) {
    assert min < max;

    return min + random.nextFloat() * (max - min);
  }
}
