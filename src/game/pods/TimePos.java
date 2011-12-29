package game.pods;

import math.Vector2;

public class TimePos {
  public final float time;
  public final Vector2 pos;

  public TimePos(float time, Vector2 pos) {
    this.time = time;
    this.pos  = new Vector2(pos);
  }
}