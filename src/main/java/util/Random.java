/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package util;

public class Random {
  private final java.util.Random mRandom = new java.util.Random();

  public boolean nextBool() {
    return mRandom.nextBoolean();
  }

  public int nextInt() {
    return mRandom.nextInt();
  }

  public int nextInt(int max) {
    assert max > 0;

    return mRandom.nextInt(max);
  }

  public int nextInt(int min, int max) {
    assert min < max;

    return min + mRandom.nextInt(max - min);
  }

  public float nextFloat() {
    return mRandom.nextFloat();
  }

  public float nextFloat(float max) {
    assert max > 0;

    return mRandom.nextFloat() * max;
  }

  public float nextFloat(float min, float max) {
    assert min < max;

    return min + mRandom.nextFloat() * (max - min);
  }
}
