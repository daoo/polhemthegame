/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package events;

import components.basic.IUnit;

public interface ICollisionEvent {
  public boolean fire(final IUnit self, final IUnit other);
}
