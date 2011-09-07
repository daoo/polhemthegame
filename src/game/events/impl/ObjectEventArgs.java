/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.events.impl;

import game.entities.interfaces.IObject;
import game.world.World;

public class ObjectEventArgs extends EventArgs {
  private final IObject object;

  public ObjectEventArgs(final World world, final IObject object) {
    super(world);
    this.object = object;
  }

  public IObject getObject() {
    return object;
  }
}
