/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.misc;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.interfaces.ILogicComponent;
import game.entities.Entity;
import game.pods.GameTime;
import game.triggers.IEffect;

import java.util.LinkedList;

public class EffectsOnDeath implements ILogicComponent {
  private final LinkedList<IEffect> effects;

  private final Entity owner;

  public EffectsOnDeath(Entity owner) {
    this.owner = owner;

    effects = new LinkedList<>();
  }

  public EffectsOnDeath(Entity owner, IEffect effect) {
    this(owner);
    effects.add(effect);
  }

  public void add(IEffect effect) {
    effects.add(effect);
  }

  @Override
  public void update(GameTime time) {
    // Do nothing
  }

  @Override
  public void reciveMessage(ComponentMessage message, Object args) {
    if (message == ComponentMessage.KILL) {
      owner.addEffects(effects);
    }
  }

  @Override
  public ComponentType getComponentType() {
    return ComponentType.ACTION_ON_DEATH;
  }
}
