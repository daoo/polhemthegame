/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package util;

import math.ExtraMath;

/**
 * A simple timer useful for testing if a certain amount of time has passed.
 */
public class Timer {
  private final long start, end;
  private final int length;

  private float current;

  /**
   * Create a new timer.
   * @param start the start time
   * @param length the length of the time interval, greater than zero
   */
  public Timer(long start, int length) {
    assert length > 0;

    this.start = start;
    this.end = start + length;
    this.length = length;
  }

  /**
   * Update the state of the time.
   * @param time the elapsed game time
   */
  public void update(long elapsed) {
    current = elapsed;
  }

  /**
   * Have the time interval passed?
   * @return true or false
   */
  public boolean isFinished() {
    return current >= end;
  }

  /**
   * Get the progress.
   * @return a float in the range [0, 1]
   */
  public float getProgress() {
    if (isFinished()) {
      return 1;
    }

    return ExtraMath.clamp(0, 1, (current - start) / length);
  }
}
