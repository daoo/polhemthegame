/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package com.daoo.math.time;

import org.lwjgl.Sys;

public class Clock {
  // NOTE: All times are in seconds
  private float tStart, tLast, tNext, targetFrameTime;
  private boolean forceSync;

  public Clock(final float targetFrameTime) {
    tStart = 0;
    tLast = 0;
    tNext = 0;

    forceSync = false;

    this.targetFrameTime = targetFrameTime;
  }

  public static float getTime() {
    final double time = (double) Sys.getTime() / (double) Sys.getTimerResolution();
    return (float) time;
  }

  public void start(final float elapsed) {
    tStart = elapsed;
    tLast = elapsed;
    tLast = tStart + targetFrameTime;
  }

  public void pause(final float elapsed) {
    tLast = elapsed;
  }

  public void resume(final float elapsed) {
    final float tNow = elapsed;
    tStart += tNow - tLast; // Compensate for total runtime calculations
    tLast = tNow;
  }

  public float sync(final float elapsed) {
    forceSync = false;

    final float tNow = elapsed;
    final float tDelta = tNow - tLast;
    tLast = tNow;
    tNext = tNow + targetFrameTime;

    return tDelta;
  }

  public boolean needsSync(final float elapsed) {
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

  public float getLifetime(final float elapsed) {
    return elapsed - tStart;
  }

  public void setTargetFrameTime(final float ft) {
    targetFrameTime = ft;
  }

  public void setTargetFrameRate(final float fr) {
    if (fr != 0) {
      targetFrameTime = 1.0f / fr;
    }

    targetFrameTime = 0;
  }
}
