/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.triggers;

import game.types.GameTime;
import game.world.World;

public interface ICondition {
  boolean evaluate(GameTime time, World world);
}
