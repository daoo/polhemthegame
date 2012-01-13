/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package math;

public interface IRandom {
  /**
   * Returns a random boolean.
   * @return a random boolean
   */
  boolean nextBool();

  /**
   * Returns a random integer in range INT_MIN to INT_MAX.
   * @return a random integer
   */
  int nextInt();

  /**
   * Returns a random integer in range [0, max).
   * @param max the upper limit (exclusive), must be greater than zero
   * @return a random integer
   */
  int nextInt(int max);

  /**
   * Returns a random integer in range [min, max).
   * Note that max must be greater than min.
   * @param min the lower limit (inclusive)
   * @param max the upper limit (exclusive)
   * @return a random integer
   */
  int nextInt(int min, int max);

  /**
   * Returns a random float in range [0, 1).
   * @return a random float
   */
  float nextFloat();

  /**
   * Returns a random float in range [0, max).
   * Note that max must be greater than zero.
   * @param max the upper limit (exclusive)
   * @return a random float
   */
  float nextFloat(float max);

  /**
   * Returns a random float in range [min, max).
   * @param min the lower limit (inclusive)
   * @param max the upper limit (exclusive)
   * @return a random float
   */
  float nextFloat(float min, float max);
}
