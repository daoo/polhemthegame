package events;

import java.util.ArrayList;

import entities.interfaces.IObject;

public class EventHandler {
  private final ArrayList<IEvent> events;

  public EventHandler() {
    events = new ArrayList<IEvent>();
  }

  public void add(final IEvent event) {
    events.add(event);
  }

  public void execute(final IObject sender) {
    for (final IEvent e : events) {
      e.execute(sender);
    }
  }
}
