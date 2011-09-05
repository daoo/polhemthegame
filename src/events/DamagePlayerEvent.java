package events;

import entities.Creep;
import entities.Player;
import entities.groups.Entities;
import entities.interfaces.IObject;

public class DamagePlayerEvent implements IEvent {
  @Override
  public void execute(IObject sender, IEventArgs args) {
    ObjectEventArgs eargs = (ObjectEventArgs) args;
    float dmg = ((Creep)eargs.getObject()).getDamage();

    for (final IObject e : eargs.getWorld().get(Entities.PLAYER)) {
      ((Player) e).damage(dmg);
    }
  }
}
