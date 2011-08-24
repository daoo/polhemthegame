/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package components.interfaces;

import math.Vector2;

import org.newdawn.slick.Graphics;

import time.GameTime;

import components.physics.AABB;

public interface IEntity {
  public void update(final GameTime time);
  public void render(final Graphics g);

  public void setPosition(final Vector2 v);
  public void setVelocity(final Vector2 v);

  public AABB getBody();
}
