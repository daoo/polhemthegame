/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package basics;

import other.GameTime;

public class Timer {
  private float       tElapsed;
  private final float tLength;
  private boolean     running;

  public Timer() {
    tLength = 0;
    running = false;
  }

  public Timer(final float length) {
    tLength = length;
    running = false;
  }

  public void update(final GameTime time) {
    if (running) {
      tElapsed += time.getFrameLength();
    }
  }

  public void start(final float elapsed) {
    running = true;
    tElapsed = 0;
  }

  public void stop(final float elapsed) {
    running = false;
  }

  public float getElapsed() {
    return tElapsed;
  }

  public boolean isFinished() {
    return running && (tElapsed >= tLength);
  }

  public boolean isRunning() {
    return running;
  }
}
