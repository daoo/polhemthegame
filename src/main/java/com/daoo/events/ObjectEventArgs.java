package com.daoo.events;

import com.daoo.entities.interfaces.IObject;
import com.daoo.game.World;

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
