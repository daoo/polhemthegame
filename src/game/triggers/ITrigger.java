/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.triggers;

import game.pods.GameTime;
import game.world.World;
import debug.IDebuggable;

public interface ITrigger extends IDebuggable {
  void addCondition(ICondition condition);
  void addEffect(IEffect effect);

  void update(GameTime time);

  void setWorld(World world);

  boolean runAgain();
}
