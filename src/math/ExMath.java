/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package math;

public class ExMath {
  public static final float PI      = (float) Math.PI;
  public static final float PI_HALF = ExMath.PI / 2.0f;

  public static float square(final float x) {
    return x * x;
  }

  public static float min(final float a, final float b, final float c,
                          final float d) {
    float min = a;
    if (b < min) {
      min = b;
    }
    if (c < min) {
      min = c;
    }
    if (d < min) {
      min = d;
    }

    return min;
  }

  public static boolean inRange(final float v, final float a, final float b) {
    return (v >= a) && (v <= b);
  }
}
