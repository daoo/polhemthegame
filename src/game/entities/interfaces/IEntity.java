/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.entities.interfaces;

import game.components.physics.AABB;
import math.Vector2;


public interface IEntity extends IObject {
  public void setPosition(final Vector2 v);
  public void setVelocity(final Vector2 v);

  public AABB getBody();
  public boolean isAlive();
}
