/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects;

import game.course.World;
import game.triggers.Effect;
import game.triggers.Trigger;
import game.types.GameTime;

/**
 * Adds a new trigger to the world when this effect is executed.
 */
public class AddTriggerEffect implements Effect {
  private final Trigger mTrigger;

  public AddTriggerEffect(Trigger trigger) {
    mTrigger = trigger;
  }

  @Override
  public void execute(GameTime time, World world) {
    world.addTrigger(mTrigger);
  }
}
