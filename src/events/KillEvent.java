package events;

import entities.interfaces.IObject;

public class KillEvent implements IEvent {
  @Override
  public void execute(IObject sender, IEventArgs args) {
    ObjectEventArgs eargs = (ObjectEventArgs) args;
    eargs.getWorld().scheduleForRemoval(eargs.getObject());
  }
}
