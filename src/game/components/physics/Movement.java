package game.components.physics;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.interfaces.ILogicComponent;
import game.entities.IEntity;
import math.Vector2;
import math.time.GameTime;

public class Movement implements ILogicComponent {
  private IEntity owner;
  private final Vector2 vel;

  public Movement(final float dx, final float dy) {
    vel = new Vector2(dx, dy);
  }

  public void addVelocity(final Vector2 v) {
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
  public void reciveMessage(final ComponentMessage message, final Object args) {
    // Do nothing
  }

  @Override
  public void setOwner(final IEntity owner) {
    this.owner = owner;
  }

  public void setVelocity(final Vector2 v) {
    vel.set(v);
  }

  @Override
  public void update(final GameTime time) {
    final Vector2 tmp = vel.multiply(time.getFrameLength());
    //owner.getBody().addPosition(tmp);
  }
}
