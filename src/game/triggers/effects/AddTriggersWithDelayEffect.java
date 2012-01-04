/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects;

import game.pods.GameTime;
import game.triggers.IEffect;
import game.triggers.ITrigger;
import game.triggers.Trigger;
import game.triggers.condition.TimerCondition;
import game.world.World;

import java.util.Collection;

/**
 * Execute some effects with a delay condition to the world.
 * The delay condition starts counting when this effect is executed.
 */
public class AddTriggersWithDelayEffect implements IEffect {
  private final float delay;
  private final ITrigger delayed;

  public AddTriggersWithDelayEffect(float delay, Collection<? extends IEffect> effects) {
    this.delay = delay;

    delayed = new Trigger(false);
    delayed.addAllEffects(effects);
  }

  @Override
  public void execute(GameTime time, World world) {
    delayed.addCondition(new TimerCondition(time.elapsed, delay));
    world.addTrigger(delayed);
  }
}
