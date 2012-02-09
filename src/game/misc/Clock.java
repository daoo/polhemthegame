/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.misc;


/**
 * Simple clock that can help with syncing.
 */
public class Clock {
  // NOTE: All times are in milliseconds
  private long next;
  private final int targetFrameTime;

  /**
   * Construct a new clock.
   * @param targetFrameTime target frame time in milliseconds
   */
  public Clock(int targetFrameTime) {
    next = 0;

    this.targetFrameTime = targetFrameTime;
  }

  /**
   * Sync the clock.
   * @param elapsed the elapsed game time in milliseconds
   */
  public void sync(long elapsed) {
    next = elapsed + targetFrameTime;
  }

  /**
   * Check if we need to sync.
   * @param elapsed the elapsed game time in milliseconds
   * @return true or false if enough time has passed since last sync
   */
  public boolean needsSync(long elapsed) {
    return (targetFrameTime == 0) || (elapsed > next);
  }
}
