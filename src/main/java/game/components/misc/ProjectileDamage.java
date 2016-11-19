/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.misc;

import game.components.ILogicComponent;
import game.entities.IEntity;
import game.types.Damage;
import game.types.GameTime;
import game.types.Message;

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
}
