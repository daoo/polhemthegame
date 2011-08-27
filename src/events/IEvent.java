package events;

import entities.interfaces.IObject;

public interface IEvent {
  public void execute(IObject sender);
}
