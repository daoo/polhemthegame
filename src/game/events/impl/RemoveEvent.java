/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.events.impl;

import game.entities.IEntity;
import game.events.IEvent;

public class RemoveEvent implements IEvent<ObjectEventArgs> {
  @Override
  public void execute(IEntity sender, ObjectEventArgs args) {
    args.getObject().remove();
  }
}
