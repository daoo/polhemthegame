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
  private final Entity owner;
  private final Rectangle bounds;

  public OutOfBounds(Entity owner, Rectangle bounds) {
    this.owner = owner;
    this.bounds = bounds;
  }

  @Override
  public void update(GameTime time) {
    if (!Rectangle.contains(bounds, owner.body)) {
      owner.remove();
    }
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    // Do nothing
  }
}
