/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.triggers.actions;

import game.world.World;
import math.time.GameTime;

public interface IAction {
  public void execute(final GameTime time, final World world);
}
