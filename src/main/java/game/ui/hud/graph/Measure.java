/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.ui.hud.graph;

public class Measure {
  // NOTE: All times are in nanoseconds

  private final long mTimeDelta;

  private long mTotal;
  private long mMeasures;
  private long mFirstMeasure;
  private long mLastMeasure;

  private boolean mFinished;

  /**
   * Construct a new measure with the specified interval.
   *
   * @param timeDelta the time difference between total measures, in nanoseconds
   */
  public Measure(long timeDelta) {
    mTimeDelta = timeDelta;

    reset();
  }

  public void startMeasure() {
    long timeCurrent = System.nanoTime();

    if (mFirstMeasure == 0) {
      mFirstMeasure = timeCurrent;
    }

    mLastMeasure = timeCurrent;
  }

  public void stopMeasure() {
    long timeCurrent = System.nanoTime();
    long delta = timeCurrent - mLastMeasure;

    mTotal += delta;
    ++mMeasures;

    if (timeCurrent - mFirstMeasure > mTimeDelta) {
      mFinished = true;
    }
  }

  public void reset() {
    mFirstMeasure = 0;
    mLastMeasure = 0;

    mTotal = 0;
    mMeasures = 0;

    mFinished = false;
  }

  public long getAverage() {
    return mTotal / mMeasures;
  }

  public boolean isFinished() {
    return mFinished;
  }
}
