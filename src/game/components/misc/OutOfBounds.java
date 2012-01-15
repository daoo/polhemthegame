/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.misc;

import game.components.Message;
import game.components.interfaces.ILogicComponent;
import game.entities.Entity;
import game.pods.GameTime;
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
