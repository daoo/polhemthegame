/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.events.impl;

import game.components.ComponentMessage;
import game.entities.EntityType;
import game.entities.IEntity;
import game.events.IEvent;
import game.pods.Damage;

public class DamagePlayerEvent implements IEvent<ObjectEventArgs> {
  private final float hpLoss;

  public DamagePlayerEvent() {
    hpLoss = 10; // FIXME: Magic number
  }

  @Override
  public void execute(IEntity sender, ObjectEventArgs args) {
    if (args.getObject().getType() == EntityType.CREEP) {
      Damage damage = new Damage(args.getObject(), hpLoss);

      for (IEntity e : args.getWorld().get(EntityType.PLAYER)) {
        e.sendMessage(ComponentMessage.DAMAGE, damage);
      }
    }
  }
}
