/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.triggers.condition;

import game.time.GameTime;
import game.triggers.ICondition;
import game.world.World;

public class TimeCondition implements ICondition {
  private final float timeAfter;

  public TimeCondition(float time) {
    timeAfter = time;
  }

  @Override
  public boolean evaluate(GameTime time, World world) {
    return time.elapsed >= timeAfter;
  }
}
