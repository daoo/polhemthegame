package game.components.misc;

import game.actions.IAction;
import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.interfaces.ILogicComponent;
import game.entities.IEntity;
import math.time.GameTime;

public class ActionOnDeath implements ILogicComponent {
  private final IAction action;

  private IEntity owner;

  public ActionOnDeath(final IAction action) {
    this.action = action;
  }

  @Override
  public void update(final GameTime time) {
    // Do nothing
  }

  @Override
  public void reciveMessage(final ComponentMessage message, final Object args) {
    if (message == ComponentMessage.KILL) {
      owner.addAction(action);
    }
  }

  @Override
  public ComponentType getComponentType() {
    return ComponentType.ACTION_ON_DEATH;
  }

  @Override
  public void setOwner(final IEntity owner) {
    this.owner = owner;
  }
}
