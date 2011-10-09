/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.events.impl;

import game.entities.interfaces.IEntity;
import game.events.IEvent;

public class KillEvent implements IEvent<ObjectEventArgs> {
  @Override
  public void execute(final IEntity sender, final ObjectEventArgs args) {
    args.getWorld().remove(args.getObject());
  }
}
