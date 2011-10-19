/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.physics;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.interfaces.ILogicComponent;
import game.entities.IEntity;
import math.Vector2;
import math.time.GameTime;

public class Gravity implements ILogicComponent {
  private static final float FACTOR = 100.0f;

  private Movement movement;

  public Gravity() {
  }

  @Override
  public void update(GameTime time) {
    float g = time.getFrameLength() * FACTOR;
    movement.addVelocity(new Vector2(0, g));
  }

  @Override
  public void reciveMessage(ComponentMessage message, Object args) {
    // Do nothing
  }

  @Override
  public ComponentType getComponentType() {
    return ComponentType.GRAVITY;
  }

  @Override
  public void setOwner(IEntity owner) {
    this.movement = (Movement) owner.getComponent(ComponentType.MOVEMENT);
  }
}
