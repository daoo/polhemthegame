/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.triggers.condition;

import game.course.World;
import game.triggers.ICondition;
import game.types.GameTime;

public class AbsoluteTimerCondition implements ICondition {
  private final long mTimeAfter;

  public AbsoluteTimerCondition(long start, int time) {
    mTimeAfter = start + time;
  }

  @Override
  public boolean evaluate(GameTime time, World world) {
    return time.elapsedMilli >= mTimeAfter;
  }
}
