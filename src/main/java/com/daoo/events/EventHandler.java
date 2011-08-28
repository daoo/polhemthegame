package com.daoo.events;

import java.util.ArrayList;

import com.daoo.entities.interfaces.IObject;

public class EventHandler {
  private final ArrayList<IEvent> events;

  public EventHandler() {
    events = new ArrayList<IEvent>();
  }

  public void add(final IEvent event) {
    assert (event != null);

    events.add(event);
  }

  public void execute(final IObject sender, final IEventArgs args) {
    for (final IEvent e : events) {
      e.execute(sender, args);
    }
  }
}
