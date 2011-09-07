/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.events;

import game.entities.interfaces.IObject;

import java.util.ArrayList;

public class EventHandler<T extends IEventArgs> {
  private final ArrayList<IEvent<T>> events;

  public EventHandler() {
    events = new ArrayList<IEvent<T>>();
  }

  public void add(final IEvent<T> event) {
    assert (event != null);

    events.add(event);
  }

  public void execute(final IObject sender, final T args) {
    for (final IEvent<T> e : events) {
      e.execute(sender, args);
    }
  }
}
