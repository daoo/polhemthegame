/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.physics;

import game.components.ComponentType;
import game.components.Message;
import game.components.interfaces.ILogicComponent;
import game.entities.IEntity;
import game.pods.GameTime;

import java.util.LinkedList;

import math.CollisionHelper;
import math.Rectangle;
import math.Vector2;

public class MovingProjectileCollision implements ILogicComponent {
  private boolean enableCollisions;
  private final LinkedList<IEntity> collidedWith;

  private final IEntity owner;
  private final Movement movement;

  public MovingProjectileCollision(IEntity owner, Movement movement) {
    this.owner    = owner;
    this.movement = movement;

    enableCollisions = true;
    collidedWith     = new LinkedList<>();
  }

  /**
   * Performs collision check and response between the owner and a different
   * entity.
   */
  private void collisionCheck(IEntity b, float dt) {
    if (!collidedWith.contains(b)) {
      Rectangle a = owner.getBody();
      Vector2 m = movement.getVelocity();

      if (CollisionHelper.sweepCollisionTest(a, m, b.getBody(), dt)) {
        owner.sendMessage(Message.COLLIDED_WITH, b);
        b.sendMessage(Message.COLLIDED_WITH, owner);
        collidedWith.add(b);
      }
    }
  }

  @Override
  public void update(GameTime time) {
    if (enableCollisions) {
      for (IEntity e : owner.getWorld().getUnits()) {
        collisionCheck(e, time.frame);
      }
    }
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    if (message == Message.KILL) {
      enableCollisions = false;
    }
  }

  @Override
  public ComponentType getComponentType() {
    return ComponentType.MOVING_COLLISION;
  }
}
