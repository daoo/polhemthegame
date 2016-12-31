/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.misc;

import java.util.Collection;

import game.components.ILogicComponent;
import game.entities.Entity;
import game.triggers.IEffect;
import game.types.GameTime;
import game.types.Message;

public class EffectsOnDeath implements ILogicComponent {
  private final Entity mOwner;
  private final Collection<? extends IEffect> mEffects;

  public EffectsOnDeath(Entity owner, Collection<? extends IEffect> effects) {
    mOwner = owner;
    mEffects = effects;
  }

  @Override
  public void update(GameTime time) {
    // Do nothing
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    if (message == Message.KILL) {
      mOwner.addEffects(mEffects);
    }
  }

  @Override
  public String toString() {
    return "EffectsOnDeath - " + mEffects.size() + " effects";
  }
}
