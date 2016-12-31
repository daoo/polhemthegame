/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package util;


/**
 * Simple clock that can help with syncing.
 */
public class Clock {
  // NOTE: All times are in milliseconds
  private long mNext;
  private final int mTargetFrameTime;

  /**
   * Construct a new clock.
   *
   * @param targetFrameTime target frame time in milliseconds
   */
  public Clock(int targetFrameTime) {
    mNext = 0;
    mTargetFrameTime = targetFrameTime;
  }

  /**
   * Sync the clock.
   *
   * @param elapsed the elapsed game time in milliseconds
   */
  public void sync(long elapsed) {
    mNext = elapsed + mTargetFrameTime;
  }

  /**
   * Check if we need to sync.
   *
   * @param elapsed the elapsed game time in milliseconds
   * @return true or false if enough time has passed since last sync
   */
  public boolean needsSync(long elapsed) {
    return mTargetFrameTime == 0 || elapsed > mNext;
  }
}
