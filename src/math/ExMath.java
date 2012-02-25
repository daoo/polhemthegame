/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package math;

// TODO: Remove dead code

public class ExMath {
  public static final float PI          = (float) Math.PI;
  public static final float PI_HALF     = ExMath.PI / 2.0f;
  public static final float RAD_PER_DEG = ExMath.PI / 180.0f;

  public static int square(int x) {
    return x * x;
  }

  public static float square(float x) {
    return x * x;
  }

  /**
   * Check if an float is with in a range (lower inclusive, upper exclusive).
   * Mathematically a <= v < b. The interval must be a proper interval, that is
   * a must be strictly smaller than b.
   * @param v the float to check
   * @param a the lower end of the interval (inclusive)
   * @param b the higher end of the interval (exclusive)
   * @return true or false
   */
  public static boolean inRange(float v, float a, float b) {
    assert a < b;

    return (v >= a) && (v < b);
  }

  public static float clamp(float min, float max, float value) {
    if (value < min)
      return min;
    else if (value > max)
      return max;
    else
      return value;
  }

  /**
   * Convert degrees to radians.
   * @param deg the degrees as an int
   * @return the same angle in radians
   */
  public static float degToRad(int deg) {
    return deg * RAD_PER_DEG;
  }
}
