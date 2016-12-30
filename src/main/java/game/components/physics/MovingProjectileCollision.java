/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.physics;

import java.util.ArrayList;

import game.components.ILogicComponent;
import game.entities.Entity;
import game.entities.IEntity;
import game.types.GameTime;
import game.types.Message;
import math.Collisions;
import math.Rectangle;
import math.Vector2;

public class MovingProjectileCollision implements ILogicComponent {
  private boolean enableCollisions;
  private final ArrayList<IEntity> collidedWith;

  private final Entity owner;
  private final Movement movement;

  public MovingProjectileCollision(Entity owner, Movement movement) {
    this.owner = owner;
    this.movement = movement;

    enableCollisions = true;
    collidedWith = new ArrayList<>();
  }

  /**
   * Performs collision check and response between the owner and a different
   * entity.
   */
  private void collisionCheck(Entity b, float dt) {
    if (!collidedWith.contains(b)) {
      Rectangle a = owner.body;
      Vector2 m = movement.getVelocity();

      if (Collisions.sweepCollisionTest(a, m, b.body, dt)) {
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
