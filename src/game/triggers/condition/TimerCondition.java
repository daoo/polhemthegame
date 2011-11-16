/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package game.triggers.condition;

import game.time.GameTime;
import game.triggers.ICondition;
import game.world.World;

public class TimerCondition implements ICondition {
  private final float timeAfter;

  public TimerCondition(float start, float time) {
    timeAfter = start + time;
  }

  @Override
  public boolean evaluate(GameTime time, World world) {
    return time.elapsed >= timeAfter;
  }
}