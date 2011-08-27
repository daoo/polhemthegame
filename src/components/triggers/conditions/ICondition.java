/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package components.triggers.conditions;

import game.World;
import math.time.GameTime;

public interface ICondition {
  public boolean evaluate(final GameTime time, final World world);
}
