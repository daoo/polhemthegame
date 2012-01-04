/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects;

import game.pods.GameTime;
import game.triggers.IEffect;
import game.triggers.ITrigger;
import game.triggers.Trigger;
import game.triggers.condition.AbsoluteTimerCondition;
import game.world.World;

import java.util.Arrays;
import java.util.Collection;

/**
 * Execute some triggers with a delay condition to the world.
 * The delay condition starts counting when this effect is executed.
 */
public class ExecuteWithDelayEffect implements IEffect {
  private final float delay;
  private final ITrigger delayed;

  public ExecuteWithDelayEffect(float delay, Collection<? extends IEffect> effects) {
    this.delay = delay;

    delayed = new Trigger(false);
    delayed.addAllEffects(effects);
  }

  public ExecuteWithDelayEffect(float delay, IEffect effect) {
    this(delay, Arrays.asList(effect));
  }

  @Override
  public void execute(GameTime time, World world) {
    delayed.addCondition(new AbsoluteTimerCondition(time.elapsed, delay));
    world.addTrigger(delayed);
  }
}
