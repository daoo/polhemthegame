package events;

import entities.interfaces.IObject;

public class DamagePlayerEvent implements IEvent {
  @Override
  public void execute(IObject sender, IEventArgs args) {
    ObjectEventArgs eargs = (ObjectEventArgs) args;
  }
}
