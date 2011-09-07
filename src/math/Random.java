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
  public int nextInt() {
    return random.nextInt();
  }

  @Override
  public int nextInt(final int max) {
    return random.nextInt(max);
  }

  @Override
  public int nextInt(final int min, final int max) {
    return min + random.nextInt(max - min);
  }

  @Override
  public float nextFloat() {
    return random.nextFloat();
  }

  @Override
  public float nextFloat(final float max) {
    return random.nextFloat() * max;
  }

  @Override
  public float nextFloat(final float min, final float max) {
    return min + random.nextFloat() * (max - min);
  }
}
