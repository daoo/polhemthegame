/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.actions;

import game.world.World;
import math.time.GameTime;

public interface IAction {
  void execute(GameTime time, World world);
}
