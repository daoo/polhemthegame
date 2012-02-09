/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.misc;

import game.components.interfaces.ILogicComponent;
import game.entities.Entity;
import game.triggers.IEffect;
import game.types.GameTime;
import game.types.Message;

import java.util.Collection;

public class EffectsOnDeath implements ILogicComponent {
  private final Entity owner;
  private final Collection<? extends IEffect> effects;

  public EffectsOnDeath(Entity owner, Collection<? extends IEffect> effects) {
    this.owner   = owner;
    this.effects = effects;
  }

  @Override
  public void update(GameTime time) {
    // Do nothing
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    if (message == Message.KILL) {
      owner.addEffects(effects);
    }
  }

  @Override
  public String toString() {
    return "EffectsOnDeath - " + effects.size() + " effects";
  }
}
