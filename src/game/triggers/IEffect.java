/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package game.triggers;

import game.time.GameTime;
import game.world.World;

public interface IEffect {
  void execute(GameTime time, World world);
}
