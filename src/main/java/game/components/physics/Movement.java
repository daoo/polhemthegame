/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.physics;

import game.components.ILogicComponent;
import game.entities.Entity;
import game.types.GameTime;
import game.types.Message;
import math.Vector2;

public class Movement implements ILogicComponent {
  private final Entity owner;
  private Vector2 vel;

  public Movement(Entity owner, float dx, float dy) {
    this.owner = owner;
    vel = new Vector2(dx, dy);
  }

  public void addVelocity(Vector2 v) {
    vel = Vector2.add(vel, v);
  }

  public Vector2 getVelocity() {
    return vel;
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    // Do nothing
  }

  public void setVelocity(Vector2 v) {
    vel = v;
  }

  @Override
  public void update(GameTime time) {
    Vector2 tmp = Vector2.multiply(vel, time.frame);
    owner.body.addPosition(tmp);
  }

  @Override
  public String toString() {
    return "Movement - " + vel;
  }
}
