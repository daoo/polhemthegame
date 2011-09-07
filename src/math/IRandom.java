/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package math;

public interface IRandom {
  /**
   * Returns a random integer in range INT_MIN to INT_MAX.
   * @return a random integer
   */
  int nextInt();

  /**
   * Returns a random integer in range 0 to max - 1.
   * @param max the upper limit (exclusive)
   * @return a random integer
   */
  int nextInt(int max);

  /**
   * Returns a random integer in range min to max.
   * @param min the lower limit (inclusive)
   * @param max the upper limit (exclusive)
   * @return a random integer
   */
  int nextInt(int min, int max);

  float nextFloat();
  float nextFloat(float max);
  float nextFloat(float f, float g);
}
