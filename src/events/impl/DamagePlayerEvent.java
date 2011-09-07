package events.impl;

import entities.Creep;
import entities.Player;
import entities.groups.Entities;
import entities.interfaces.IObject;
import events.IEvent;

public class DamagePlayerEvent implements IEvent<ObjectEventArgs> {
  @Override
  public void execute(final IObject sender, final ObjectEventArgs args) {
    float dmg = ((Creep)args.getObject()).getDamage();

    for (final IObject e : args.getWorld().get(Entities.PLAYER)) {
      ((Player) e).damage(dmg);
    }
  }
}
