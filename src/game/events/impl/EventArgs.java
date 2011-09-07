/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.events.impl;

import game.events.IEventArgs;
import game.world.World;

public class EventArgs implements IEventArgs {
  private final World world;

  public EventArgs(final World world) {
    this.world = world;
  }

  public World getWorld() {
    return world;
  }
}
