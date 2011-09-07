/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.events;

import game.entities.interfaces.IObject;

public interface IEvent<T extends IEventArgs> {
  void execute(final IObject sender, final T args);
}
