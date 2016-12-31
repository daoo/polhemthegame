/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.misc;

import game.components.ILogicComponent;
import game.entities.Entity;
import game.types.GameTime;
import game.types.Message;
import math.Rectangle;

public class OutOfBounds implements ILogicComponent {
  private final Entity mOwner;
  private final Rectangle mBounds;

  public OutOfBounds(Entity owner, Rectangle bounds) {
    mOwner = owner;
    mBounds = bounds;
  }

  @Override
  public void update(GameTime time) {
    if (!Rectangle.contains(mBounds, mOwner.getBody())) {
      mOwner.remove();
    }
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    // Do nothing
  }
}
