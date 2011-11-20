/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects;

import game.time.GameTime;
import game.triggers.IEffect;
import game.triggers.ITrigger;
import game.triggers.Trigger;
import game.triggers.condition.TimerCondition;
import game.world.World;

public class DelayedActivateTriggerEffect implements IEffect {
  private final float delay;
  private final ITrigger trigger;

  public DelayedActivateTriggerEffect(float delay, ITrigger trigger) {
    this.delay   = delay;
    this.trigger = trigger;
  }

  @Override
  public void execute(GameTime time, World world) {
    Trigger delayed = new Trigger(false);
    delayed.addCondition(new TimerCondition(time.elapsed, delay));
    delayed.addEffect(new NewTriggerEffect(trigger));
    world.addTrigger(delayed);
  }
}
