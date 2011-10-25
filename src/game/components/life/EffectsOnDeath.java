/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.life;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.interfaces.ILogicComponent;
import game.entities.IEntity;
import game.time.GameTime;
import game.triggers.IEffect;

import java.util.LinkedList;

public class EffectsOnDeath implements ILogicComponent {
  private final LinkedList<IEffect> effects;

  private IEntity owner;

  public EffectsOnDeath() {
    effects = new LinkedList<IEffect>();
  }

  public EffectsOnDeath(IEffect effect) {
    this();
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
      owner.addActions(effects);
    }
  }

  @Override
  public ComponentType getComponentType() {
    return ComponentType.ACTION_ON_DEATH;
  }

  @Override
  public void setOwner(IEntity owner) {
    this.owner = owner;
  }
}
