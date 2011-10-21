package game.components.misc;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.interfaces.ILogicComponent;
import game.entities.IEntity;
import game.time.GameTime;

public class ProjectileDamage implements ILogicComponent {

  @Override
  public void update(GameTime time) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void reciveMessage(ComponentMessage message, Object args) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public ComponentType getComponentType() {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void setOwner(IEntity owner) {
    throw new UnsupportedOperationException("Not implemented");
  }

}
