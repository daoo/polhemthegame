/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects;

import game.time.GameTime;
import game.triggers.IEffect;
import game.triggers.ITrigger;
import game.world.World;

class NewTriggerEffect implements IEffect {
  private final ITrigger trigger;

  public NewTriggerEffect(ITrigger trigger) {
    this.trigger = trigger;
  }

  public void execute(GameTime time, World world) {
    world.addTrigger(trigger);
  }
}