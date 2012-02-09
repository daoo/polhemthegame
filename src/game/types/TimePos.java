/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.types;

import math.Vector2;

public class TimePos {
  public final long time;
  public final Vector2 pos;

  public TimePos(long time, Vector2 pos) {
    this.time = time;
    this.pos  = pos;
  }
}
