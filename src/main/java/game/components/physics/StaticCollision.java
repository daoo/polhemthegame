/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.physics;

import game.components.ILogicComponent;
import game.entities.Entity;
import game.types.GameTime;
import game.types.Message;

public class StaticCollision implements ILogicComponent {
  private final Entity mOwner;
  private boolean mEnableCollisions;

  public StaticCollision(Entity owner) {
    mOwner = owner;
    mEnableCollisions = true;
  }

  @Override
  public void update(GameTime time) {
    if (mEnableCollisions) {
      for (Entity e : mOwner.getWorld().getUnits()) {
        if (mOwner.getBody().intersects(e.getBody())) {
          mOwner.sendMessage(Message.COLLIDED_WITH, e);
          e.sendMessage(Message.COLLIDED_WITH, mOwner);
        }
      }
    }
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    if (message == Message.KILL) {
      mEnableCollisions = false;
    }
  }
}
