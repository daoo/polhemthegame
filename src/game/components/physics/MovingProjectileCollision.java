/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.physics;

import game.components.Message;
import game.components.interfaces.ILogicComponent;
import game.entities.Entity;
import game.entities.IEntity;
import game.pods.GameTime;

import java.util.LinkedList;

import math.CollisionHelper;
import math.Rectangle;
import math.Vector2;

public class MovingProjectileCollision implements ILogicComponent {
  private boolean enableCollisions;
  private final LinkedList<IEntity> collidedWith;

  private final Entity owner;
  private final Movement movement;

  public MovingProjectileCollision(Entity owner, Movement movement) {
    this.owner    = owner;
    this.movement = movement;

    enableCollisions = true;
    collidedWith     = new LinkedList<>();
  }

  /**
   * Performs collision check and response between the owner and a different
   * entity.
   */
  private void collisionCheck(Entity b, float dt) {
    if (!collidedWith.contains(b)) {
      Rectangle a = owner.body;
      Vector2 m = movement.getVelocity();

      if (CollisionHelper.sweepCollisionTest(a, m, b.body, dt)) {
        owner.sendMessage(Message.COLLIDED_WITH, b);
        b.sendMessage(Message.COLLIDED_WITH, owner);
        collidedWith.add(b);
      }
    }
  }

  @Override
  public void update(GameTime time) {
    if (enableCollisions) {
      for (Entity e : owner.getWorld().getUnits()) {
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
}
