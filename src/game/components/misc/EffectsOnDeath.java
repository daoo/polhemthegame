/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.misc;

import game.components.Message;
import game.components.interfaces.ILogicComponent;
import game.entities.Entity;
import game.pods.GameTime;
import game.triggers.IEffect;

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
}
