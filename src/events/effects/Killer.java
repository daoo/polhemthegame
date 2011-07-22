/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package events.effects;

import components.basic.IUnit;

import events.ICollisionEvent;

public class Killer implements ICollisionEvent {
  @Override
  public boolean fire(final IUnit self, final IUnit other) {
    other.kill();

    return false;
  }
}
