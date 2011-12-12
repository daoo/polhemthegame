/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.physics;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.interfaces.ILogicComponent;
import game.entities.IEntity;
import game.time.GameTime;

import java.util.LinkedList;

import math.CollisionHelper;
import math.Rectangle;
import math.Vector2;

public class ProjectileCollision implements ILogicComponent {
  private boolean enableCollisions;
  private final LinkedList<IEntity> collidedWith;

  private final IEntity owner;
  private final Movement movement;

  public ProjectileCollision(IEntity owner, Movement movement) {
    this.owner    = owner;
    this.movement = movement;

    enableCollisions = true;
    collidedWith     = new LinkedList<>();
  }

  private void collisionCheck(Rectangle a, Vector2 m, IEntity b, float dt) {
    if (!collidedWith.contains(b)) {
      if (CollisionHelper.sweepCollisionTest(a, m, b.getBody(), dt)) {
        owner.sendMessage(ComponentMessage.COLLIDED_WITH, b);

        collidedWith.add(b);
      }
    }
  }

  @Override
  public void update(GameTime time) {
    if (enableCollisions) {
      for (IEntity e : owner.getWorld().getUnits()) {
        collisionCheck(owner.getBody(), movement.getVelocity(), e, time.frame);
      }
    }
  }

  @Override
  public void reciveMessage(ComponentMessage message, Object args) {
    if (message == ComponentMessage.KILL) {
      enableCollisions = false;
    }
  }

  @Override
  public ComponentType getComponentType() {
    return ComponentType.COLLISION;
  }
}
