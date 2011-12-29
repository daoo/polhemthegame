package game.components.physics;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.interfaces.ILogicComponent;
import game.entities.IEntity;
import game.pods.GameTime;

public class StaticCollision implements ILogicComponent {
  private final IEntity owner;

  private boolean enableCollisions;

  public StaticCollision(IEntity owner) {
    this.owner = owner;

    enableCollisions = true;
  }

  @Override
  public void update(GameTime time) {
    if (enableCollisions) {
      for (IEntity e : owner.getWorld().getUnits()) {
        if (owner.getBody().isIntersecting(e.getBody())) {
          owner.sendMessage(ComponentMessage.COLLIDED_WITH, e);
          e.sendMessage(ComponentMessage.COLLIDED_WITH, owner);
        }
      }
    }
  }

  @Override
  public void reciveMessage(ComponentMessage message, Object args) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public ComponentType getComponentType() {
    throw new UnsupportedOperationException("Not implemented");
  }

}
