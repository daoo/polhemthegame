/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.misc;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.interfaces.ILogicComponent;
import game.entities.IEntity;
import game.pods.GameTime;
import math.CollisionHelper;
import math.Rectangle;

public class MovementConstraint implements ILogicComponent {
  private final IEntity owner;
  private final Rectangle constraints;

  public MovementConstraint(IEntity owner, Rectangle constraints) {
    this.owner       = owner;
    this.constraints = constraints;
  }

  @Override
  public void update(GameTime time) {
    CollisionHelper.blockFromExiting(owner.getBody(), constraints);
  }

  @Override
  public void reciveMessage(ComponentMessage message, Object args) {
    // Do nothing
  }

  @Override
  public ComponentType getComponentType() {
    return ComponentType.MOVEMENT_CONSTRAINTS;
  }
}
