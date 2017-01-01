/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.physics;

import java.util.ArrayList;

import game.components.LogicComponent;
import game.entities.EntityImpl;
import game.entities.Entity;
import game.types.GameTime;
import game.types.Message;
import math.Aabb;
import math.Collisions;
import math.Vector2;

public class MovingProjectileCollision implements LogicComponent {
  private boolean mEnableCollisions;
  private final ArrayList<Entity> mCollidedWith;

  private final EntityImpl mOwner;
  private final Movement mMovement;

  public MovingProjectileCollision(EntityImpl owner, Movement movement) {
    mOwner = owner;
    mMovement = movement;

    mEnableCollisions = true;
    mCollidedWith = new ArrayList<>();
  }

  /**
   * Performs collision check and response between the owner and a different
   * entity.
   */
  private void collisionCheck(EntityImpl b, float dt) {
    if (!mCollidedWith.contains(b)) {
      Aabb a = mOwner.getBody();
      Vector2 m = mMovement.getVelocity();

      if (Collisions.sweepCollisionTest(a, m, b.getBody(), dt)) {
        mOwner.sendMessage(Message.COLLIDED_WITH, b);
        b.sendMessage(Message.COLLIDED_WITH, mOwner);
        mCollidedWith.add(b);
      }
    }
  }

  @Override
  public void update(GameTime time) {
    if (mEnableCollisions) {
      for (EntityImpl e : mOwner.getWorld().getUnits()) {
        collisionCheck(e, time.frame);
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
