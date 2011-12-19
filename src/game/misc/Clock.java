/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.misc;

import org.lwjgl.Sys;

public class Clock {
  // NOTE: All times are in seconds
  private float tStart, tLast, tNext, targetFrameTime;
  private boolean forceSync;

  public Clock(float targetFrameTime) {
    tStart = 0;
    tLast = 0;
    tNext = 0;

    forceSync = false;

    this.targetFrameTime = targetFrameTime;
  }

  public static float getTime() {
    double time = (double) Sys.getTime() / (double) Sys.getTimerResolution();
    return (float) time;
  }

  public void start(float elapsed) {
    tStart = elapsed;
    tLast = elapsed;
    tLast = tStart + targetFrameTime;
  }

  public void pause(float elapsed) {
    tLast = elapsed;
  }

  public void resume(float elapsed) {
    float tNow = elapsed;
    tStart += tNow - tLast; // Compensate for total runtime calculations
    tLast = tNow;
  }

  public float sync(float elapsed) {
    forceSync = false;

    float tNow = elapsed;
    float tDelta = tNow - tLast;
    tLast = tNow;
    tNext = tNow + targetFrameTime;

    return tDelta;
  }

  public boolean needsSync(float elapsed) {
    return forceSync || (targetFrameTime == 0) || (elapsed > tNext);
  }

  public void forceSync() {
    forceSync = true;
  }

  public float getTargetFrameTime() {
    return targetFrameTime;
  }

  public float getTargetFrameRate() {
    if (targetFrameTime != 0) {
      return 1.0f / targetFrameTime;
    }

    return 0;
  }

  public float getLifetime(float elapsed) {
    return elapsed - tStart;
  }

  public void setTargetFrameTime(float ft) {
    targetFrameTime = ft;
  }

  public void setTargetFrameRate(float fr) {
    if (fr != 0) {
      targetFrameTime = 1.0f / fr;
    }

    targetFrameTime = 0;
  }
}
