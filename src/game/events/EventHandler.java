/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.events;

import game.entities.IEntity;

import java.util.ArrayList;

public class EventHandler<T extends IEventArgs> {
  private final ArrayList<IEvent<T>> events;

  public EventHandler() {
    events = new ArrayList<IEvent<T>>();
  }

  public void add(IEvent<T> event) {
    assert (event != null);

    events.add(event);
  }

  public void execute(IEntity sender, T args) {
    for (IEvent<T> e : events) {
      e.execute(sender, args);
    }
  }
}
