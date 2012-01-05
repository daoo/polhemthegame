/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.pods;

import math.Vector2;

public class TimePos {
  public final float time;
  public final Vector2 pos;

  public TimePos(float time, Vector2 pos) {
    this.time = time;
    this.pos  = pos;
  }
}
