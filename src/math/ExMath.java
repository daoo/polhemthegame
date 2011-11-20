/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package math;

public class ExMath {
  public static final float PI      = (float) Math.PI;
  public static final float PI_HALF = ExMath.PI / 2.0f;

  public static float square(float x) {
    return x * x;
  }

  public static float min(float a, float b, float c, float d) {
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

  public static boolean inRange(float v, float a, float b) {
    return (v >= a) && (v <= b);
  }

  public static float clamp(float min, float max, float value) {
    if (value < min)
      return min;
    else if (value > max)
      return max;
    else
      return value;
  }
}
