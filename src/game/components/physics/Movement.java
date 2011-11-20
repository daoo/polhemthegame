/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.physics;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.interfaces.ILogicComponent;
import game.entities.IEntity;
import game.time.GameTime;
import math.Vector2;

public class Movement implements ILogicComponent {
  private final IEntity owner;
  private final Vector2 vel;

  public Movement(IEntity owner, float dx, float dy) {
    this.owner = owner;
    vel = new Vector2(dx, dy);
  }

  public void addVelocity(Vector2 v) {
    vel.addSelf(v);
  }

  @Override
  public ComponentType getComponentType() {
    return ComponentType.MOVEMENT;
  }

  public Vector2 getVelocity() {
    return vel;
  }

  @Override
  public void reciveMessage(ComponentMessage message, Object args) {
    // Do nothing
  }

  public void setVelocity(Vector2 v) {
    vel.set(v);
  }

  @Override
  public void update(GameTime time) {
    Vector2 tmp = vel.multiply(time.frame);
    owner.getBody().addPosition(tmp);
  }
}
