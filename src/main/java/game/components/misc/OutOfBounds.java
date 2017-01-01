/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.misc;

import game.components.ILogicComponent;
import game.entities.Entity;
import game.types.GameTime;
import game.types.Message;
import math.Aabb;

public class OutOfBounds implements ILogicComponent {
  private final Entity mOwner;
  private final Aabb mBoundary;

  public OutOfBounds(Entity owner, Aabb boundary) {
    mOwner = owner;
    mBoundary = boundary;
  }

  @Override
  public void update(GameTime time) {
    if (!mBoundary.contains(mOwner.getBody())) {
      mOwner.remove();
    }
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    // Do nothing
  }
}
