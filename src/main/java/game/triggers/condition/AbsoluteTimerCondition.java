/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.triggers.condition;

import game.course.World;
import game.triggers.Condition;
import game.types.GameTime;

public class AbsoluteTimerCondition implements Condition {
  private final long mTimeAfter;

  public AbsoluteTimerCondition(long start, int time) {
    mTimeAfter = start + time;
  }

  @Override
  public boolean evaluate(GameTime time, World world) {
    return time.elapsedMilli >= mTimeAfter;
  }
}
