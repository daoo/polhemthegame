/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.events.impl;

import game.components.ComponentMessage;
import game.entities.IEntity;
import game.entities.groups.EntityType;
import game.events.IEvent;

public class DamagePlayerEvent implements IEvent<ObjectEventArgs> {
  @Override
  public void execute(final IEntity sender, final ObjectEventArgs args) {
    if (args.getObject().getType() == EntityType.CREEP) {
      final float dmg = 10;

      for (final IEntity e : args.getWorld().get(EntityType.PLAYER)) {
        e.sendMessage(ComponentMessage.DAMAGE, dmg);
      }
    }
  }
}
