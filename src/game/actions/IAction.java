/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.actions;

import game.time.GameTime;
import game.world.World;

public interface IAction {
  void execute(GameTime time, World world);
}
