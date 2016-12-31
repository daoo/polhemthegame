/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package util;

import math.ExtraMath;

/**
 * A simple timer useful for testing if a certain amount of time has passed.
 */
public class Timer {
  private final long mStart;
  private final long mEnd;
  private final int mLength;

  private float mCurrent;

  /**
   * Create a new timer.
   *
   * @param start the start time
   * @param length the length of the time interval, greater than zero
   */
  public Timer(long start, int length) {
    assert length > 0;

    mStart = start;
    mEnd = start + length;
    mLength = length;
  }

  /**
   * Update the state of the time.
   *
   * @param elapsed the elapsed game time
   */
  public void update(long elapsed) {
    mCurrent = elapsed;
  }

  /**
   * Have the time interval passed?
   *
   * @return true or false
   */
  public boolean isFinished() {
    return mCurrent >= mEnd;
  }

  /**
   * Get the progress.
   *
   * @return a float in the range [0, 1]
   */
  public float getProgress() {
    if (isFinished()) {
      return 1;
    }

    return ExtraMath.clamp(0, 1, (mCurrent - mStart) / mLength);
  }
}
