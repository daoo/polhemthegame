/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.misc;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.interfaces.ILogicComponent;
import game.entities.IEntity;
import game.pods.Damage;
import game.time.GameTime;

public class ProjectileDamage implements ILogicComponent {
  private final Damage damageOther;
  private final Damage damageSelf;

  private final IEntity owner;

  public ProjectileDamage(IEntity owner, IEntity source, float damage) {
    this.owner = owner;

    this.damageSelf = new Damage(null, 1);
    this.damageOther = new Damage(source, damage);
  }

  @Override
  public void update(GameTime time) {
    // Do nothing
  }

  @Override
  public void reciveMessage(ComponentMessage message, Object args) {
    if (message == ComponentMessage.COLLIDED_WITH) {
      IEntity entity = (IEntity) args;
      entity.sendMessage(ComponentMessage.DAMAGE, damageOther);
      owner.sendMessage(ComponentMessage.DAMAGE, damageSelf);
    }
  }

  @Override
  public ComponentType getComponentType() {
    return ComponentType.PROJECTILE_DAMAGE;
  }
}
