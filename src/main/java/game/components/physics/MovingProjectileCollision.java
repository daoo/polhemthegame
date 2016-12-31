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
  private boolean mEnableCollisions;
  private final ArrayList<IEntity> mCollidedWith;

  private final Entity mOwner;
  private final Movement mMovement;

  public MovingProjectileCollision(Entity owner, Movement movement) {
    mOwner = owner;
    mMovement = movement;

    mEnableCollisions = true;
    mCollidedWith = new ArrayList<>();
  }

  /**
   * Performs collision check and response between the owner and a different
   * entity.
   */
  private void collisionCheck(Entity b, float dt) {
    if (!mCollidedWith.contains(b)) {
      Rectangle a = mOwner.getBody();
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
      for (Entity e : mOwner.getWorld().getUnits()) {
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
