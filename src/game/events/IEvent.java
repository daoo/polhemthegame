/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.events;

import game.entities.IEntity;

public interface IEvent<T extends IEventArgs> {
  void execute(final IEntity sender, final T args);
}
