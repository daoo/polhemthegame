package com.daoo.events;

import com.daoo.entities.interfaces.IObject;

public interface IEvent {
  public void execute(final IObject sender, final IEventArgs args);
}
