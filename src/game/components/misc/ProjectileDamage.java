/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.misc;

import game.components.ComponentType;
import game.components.Message;
import game.components.interfaces.ILogicComponent;
import game.entities.IEntity;
import game.pods.Damage;
import game.pods.GameTime;

public class ProjectileDamage implements ILogicComponent {
  private final Damage damageOther;
  private final Damage damageSelf;

  private final IEntity owner;
  private final IEntity source;

  public ProjectileDamage(IEntity owner, IEntity source, float damage) {
    this.owner = owner;
    this.source = source;

    this.damageSelf = new Damage(null, 1);
    this.damageOther = new Damage(source, damage);
  }

  @Override
  public void update(GameTime time) {
    // Do nothing
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    if (message == Message.COLLIDED_WITH) {
      IEntity entity = (IEntity) args;
      entity.sendMessage(Message.DAMAGE, damageOther);
      owner.sendMessage(Message.DAMAGE, damageSelf);
      source.sendMessage(Message.DEALT_DAMAGE, damageOther);
    }
  }

  @Override
  public ComponentType getComponentType() {
    return ComponentType.PROJECTILE_DAMAGE;
  }
}
