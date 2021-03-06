/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.physics;

import game.components.LogicComponent;
import game.entities.EntityImpl;
import game.types.GameTime;
import game.types.Message;
import math.Aabb;
import math.Collisions;

public class MovementConstraint implements LogicComponent {
  private final EntityImpl mOwner;
  private final Aabb mConstraints;

  public MovementConstraint(EntityImpl owner, Aabb constraints) {
    mOwner = owner;
    mConstraints = constraints;
  }

  @Override
  public void update(GameTime time) {
    Collisions.blockFromExiting(mOwner.getBody(), mConstraints);
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    // Do nothing
  }

  @Override
  public String toString() {
    return "MovementConstraint - rect: " + mConstraints;
  }
}
