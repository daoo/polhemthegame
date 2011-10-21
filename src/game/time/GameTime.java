/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.time;

public class GameTime {
  public final float frame;
  public final float elapsed;

  public GameTime(float frame, float elapsed) {
    this.frame   = frame;
    this.elapsed = elapsed;
  }

  @Override
  public String toString() {
    return "Elapsed: " + elapsed + ", Delta: " + frame;
  }
}
