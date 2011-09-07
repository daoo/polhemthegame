package events;

import entities.interfaces.IObject;

public interface IEvent<T extends IEventArgs> {
  void execute(final IObject sender, final T args);
}
