/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package components.interfaces;

import org.newdawn.slick.Graphics;

import other.GameTime;
import basics.Vector2;

import components.physics.AABB;

public interface IEntity {
  public void update(final GameTime time);
  public void render(final Graphics g);

  public void setPosition(final Vector2 v);
  public void setVelocity(final Vector2 v);

  public AABB getBody();
}
