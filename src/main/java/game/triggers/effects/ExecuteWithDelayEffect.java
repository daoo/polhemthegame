/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects;

import java.util.Arrays;
import java.util.Collection;

import game.course.World;
import game.triggers.IEffect;
import game.triggers.Trigger;
import game.triggers.condition.AbsoluteTimerCondition;
import game.types.GameTime;

/**
 * Execute some triggers with a delay condition to the world.
 * The delay condition starts counting when this effect is executed.
 */
public class ExecuteWithDelayEffect implements IEffect {
  private final int mDelay;
  private final Trigger mDelayed;

  public ExecuteWithDelayEffect(int delay, Collection<? extends IEffect> effects) {
    mDelay = delay;

    mDelayed = new Trigger();
    mDelayed.addAllEffects(effects);
  }

  public ExecuteWithDelayEffect(int delay, IEffect effect) {
    this(delay, Arrays.asList(effect));
  }

  @Override
  public void execute(GameTime time, World world) {
    mDelayed.addCondition(new AbsoluteTimerCondition(time.elapsedMilli, mDelay));
    world.addTrigger(mDelayed);
  }
}
