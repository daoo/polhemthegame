/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects;

import game.triggers.IEffect;
import game.triggers.Trigger;
import game.types.GameTime;
import game.world.World;

import java.util.Arrays;
import java.util.Collection;

/**
 * Adds a new trigger to the world when this effect is executed.
 */
public class AddTriggersEffect implements IEffect {
  private final Collection<Trigger> triggers;

  public AddTriggersEffect(Collection<Trigger> triggers) {
    this.triggers = triggers;
  }

  public AddTriggersEffect(Trigger trigger) {
    this(Arrays.asList(trigger));
  }

  @Override
  public void execute(GameTime time, World world) {
    world.addAllTriggers(triggers);
  }
}
