/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.triggers;

import game.course.World;
import game.types.GameTime;

public interface ICondition {
  boolean evaluate(GameTime time, World world);
}
