/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.physics;

import game.components.ILogicComponent;
import game.entities.Entity;
import game.types.GameTime;
import game.types.Message;
import math.Collisions;
import math.Rectangle;

public class MovementConstraint implements ILogicComponent {
  private final Entity mOwner;
  private final Rectangle mConstraints;

  public MovementConstraint(Entity owner, Rectangle constraints) {
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
