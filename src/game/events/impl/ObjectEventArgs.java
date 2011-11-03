/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package game.events.impl;

import game.entities.IEntity;
import game.world.World;

public class ObjectEventArgs extends EventArgs {
  private final IEntity object;

  public ObjectEventArgs(World world, IEntity object) {
    super(world);
    this.object = object;
  }

  public IEntity getObject() {
    return object;
  }
}
