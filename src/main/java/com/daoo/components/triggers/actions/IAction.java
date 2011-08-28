/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package components.triggers.actions;

import game.World;
import math.time.GameTime;

public interface IAction {
  public void execute(final GameTime time, final World world);
}
