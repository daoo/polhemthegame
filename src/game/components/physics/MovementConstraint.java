/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.physics;

import game.components.interfaces.ILogicComponent;
import game.entities.Entity;
import game.types.GameTime;
import game.types.Message;
import math.CollisionHelper;
import math.Rectangle;

public class MovementConstraint implements ILogicComponent {
  private final Entity owner;
  private final Rectangle constraints;

  public MovementConstraint(Entity owner, Rectangle constraints) {
    this.owner       = owner;
    this.constraints = constraints;
  }

  @Override
  public void update(GameTime time) {
    CollisionHelper.blockFromExiting(owner.body, constraints);
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    // Do nothing
  }

  @Override
  public String toString() {
    return "MovementConstraint - rect: " + constraints.toString();
  }
}
