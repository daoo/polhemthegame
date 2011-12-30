/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.events.impl;

import game.components.Message;
import game.entities.EntityType;
import game.entities.IEntity;
import game.events.IEvent;
import game.pods.Damage;

import java.util.List;

public class DamageEntitiesEvent implements IEvent<ObjectEventArgs> {
  private final float hpLoss;
  private final List<? extends IEntity> entities;

  public DamageEntitiesEvent(List<? extends IEntity> entities, float damage) {
    hpLoss = damage;
    this.entities = entities;
  }

  @Override
  public void execute(IEntity sender, ObjectEventArgs args) {
    if (args.getObject().getType() == EntityType.CREEP) {
      Damage damage = new Damage(args.getObject(), hpLoss);

      for (IEntity e : entities) {
        e.sendMessage(Message.DAMAGE, damage);
      }
    }
  }
}
