/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package math.time;

public class GameTime {
  private final float frame;
  private final float elapsed;

  public GameTime(float frame, float elapsed) {
    this.frame   = frame;
    this.elapsed = elapsed;
  }

  public float getFrameLength() {
    return frame;
  }

  public float getElapsed() {
    return elapsed;
  }

  @Override
  public String toString() {
    return "Elapsed: " + elapsed + ", Delta: " + frame;
  }
}
