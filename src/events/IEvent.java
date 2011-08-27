package events;

import entities.interfaces.IObject;

public interface IEvent {
  public void execute(final IObject sender, final IEventArgs args);
}
