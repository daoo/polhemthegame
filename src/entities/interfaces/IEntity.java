/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package entities.interfaces;

import math.Vector2;

import components.physics.AABB;
import components.triggers.actions.IActionProducer;

public interface IEntity extends IActionProducer, IObject {
  public void setPosition(final Vector2 v);
  public void setVelocity(final Vector2 v);

  public AABB getBody();
  public boolean isAlive();
}
