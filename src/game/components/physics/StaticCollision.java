package game.components.physics;

import game.components.ComponentType;
import game.components.Message;
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
          owner.sendMessage(Message.COLLIDED_WITH, e);
          e.sendMessage(Message.COLLIDED_WITH, owner);
        }
      }
    }
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    if (message == Message.KILL) {
      enableCollisions = false;
    }
  }

  @Override
  public ComponentType getComponentType() {
    return ComponentType.STATIC_COLLISION;
  }
}
