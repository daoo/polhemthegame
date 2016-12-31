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
  private final Entity mOwner;
  private Vector2 mVel;

  public Movement(Entity owner, float dx, float dy) {
    mOwner = owner;
    mVel = new Vector2(dx, dy);
  }

  public void addVelocity(Vector2 v) {
    mVel = Vector2.add(mVel, v);
  }

  public Vector2 getVelocity() {
    return mVel;
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    // Do nothing
  }

  public void setVelocity(Vector2 v) {
    mVel = v;
  }

  @Override
  public void update(GameTime time) {
    Vector2 tmp = Vector2.multiply(mVel, time.frame);
    mOwner.getBody().addPosition(tmp);
  }

  @Override
  public String toString() {
    return "Movement - " + mVel;
  }
}
