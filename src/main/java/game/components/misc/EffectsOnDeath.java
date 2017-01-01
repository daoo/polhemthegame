/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.misc;

import java.util.Collection;

import game.components.LogicComponent;
import game.entities.EntityImpl;
import game.triggers.Effect;
import game.types.GameTime;
import game.types.Message;

public class EffectsOnDeath implements LogicComponent {
  private final EntityImpl mOwner;
  private final Collection<? extends Effect> mEffects;

  public EffectsOnDeath(EntityImpl owner, Collection<? extends Effect> effects) {
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
