package components.basic;

import other.GameTime;
import basics.Vector2;

import components.physics.AABB;

public interface IEntity {
  public void update(final GameTime time);
  
  public void setPosition(final Vector2 v);
  public void setVelocity(final Vector2 v);

  public AABB getBody();
}
