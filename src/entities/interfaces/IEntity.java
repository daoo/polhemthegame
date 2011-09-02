/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package com.daoo.entities.interfaces;

import com.daoo.components.physics.AABB;
import com.daoo.components.triggers.actions.IActionProducer;
import com.daoo.math.Vector2;

public interface IEntity extends IActionProducer, IObject {
  public void setPosition(final Vector2 v);
  public void setVelocity(final Vector2 v);

  public AABB getBody();
  public boolean isAlive();
}
