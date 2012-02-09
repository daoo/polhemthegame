/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.triggers;

import game.types.GameTime;
import game.world.World;

import java.util.Collection;

import debug.IDebuggable;

public interface ITrigger extends IDebuggable {
  void addCondition(ICondition condition);
  void addAllConditions(Collection<? extends ICondition> conditions);

  void addEffect(IEffect effect);
  void addAllEffects(Collection<? extends IEffect> effects);

  void update(GameTime time);

  void setWorld(World world);

  boolean runAgain();
}
