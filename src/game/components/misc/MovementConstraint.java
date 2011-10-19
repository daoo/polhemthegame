package game.components.misc;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.interfaces.ILogicComponent;
import game.entities.IEntity;
import math.CollisionHelper;
import math.Rectangle;
import math.time.GameTime;

public class MovementConstraint implements ILogicComponent {
  private IEntity owner;
  private final Rectangle constraints;

  public MovementConstraint(Rectangle constraints) {
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

  @Override
  public void setOwner(IEntity owner) {
    this.owner = owner;
  }
}
