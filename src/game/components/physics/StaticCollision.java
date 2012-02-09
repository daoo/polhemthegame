/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.physics;

import game.components.interfaces.ILogicComponent;
import game.entities.Entity;
import game.types.GameTime;
import game.types.Message;
import math.Rectangle;

public class StaticCollision implements ILogicComponent {
  private final Entity owner;

  private boolean enableCollisions;

  public StaticCollision(Entity owner) {
    this.owner = owner;

    enableCollisions = true;
  }

  @Override
  public void update(GameTime time) {
    if (enableCollisions) {
      for (Entity e : owner.getWorld().getUnits()) {
        if (Rectangle.intersects(owner.body, e.body)) {
          owner.sendMessage(Message.COLLIDED_WITH, e);
          e.sendMessage(Message.COLLIDED_WITH, owner);
        }
      }
    }
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    if (message == Message.KILL) {
      enableCollisions = false;
    }
  }
}
