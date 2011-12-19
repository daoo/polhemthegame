/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.triggers.condition;

import game.pods.GameTime;
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
