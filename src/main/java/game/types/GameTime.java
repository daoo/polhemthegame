/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.types;

@SuppressWarnings("InstanceVariableNamingConvention")
public class GameTime {
  public final float frame;

  public final int frameMilli;
  public final long elapsedMilli;

  public GameTime(float frame, int frameMilli, long elapsedMilli) {
    this.frame = frame;
    this.frameMilli = frameMilli;
    this.elapsedMilli = elapsedMilli;
  }

  @Override
  public String toString() {
    return "Elapsed: " + elapsedMilli + ", Delta: " + frameMilli;
  }
}
