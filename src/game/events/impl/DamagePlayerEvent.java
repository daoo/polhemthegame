/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.events.impl;

import game.entities.Creep;
import game.entities.Player;
import game.entities.groups.Entities;
import game.entities.interfaces.IObject;
import game.events.IEvent;

public class DamagePlayerEvent implements IEvent<ObjectEventArgs> {
  @Override
  public void execute(final IObject sender, final ObjectEventArgs args) {
    if (args.getObject().getType() == Entities.CREEP) {
      final float dmg = ((Creep)args.getObject()).getDamage();

      for (final IObject e : args.getWorld().get(Entities.PLAYER)) {
        ((Player) e).damage(dmg);
      }
    }
  }
}
