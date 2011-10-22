package game.components.life;

import game.actions.IAction;
import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.interfaces.ILogicComponent;
import game.entities.IEntity;
import game.time.GameTime;

import java.util.LinkedList;

public class ActionsOnDeath implements ILogicComponent {
  private final LinkedList<IAction> actions;

  private IEntity owner;

  public ActionsOnDeath() {
    actions = new LinkedList<IAction>();
  }

  public ActionsOnDeath(IAction action) {
    this();
    actions.add(action);
  }

  public void add(IAction action) {
    actions.add(action);
  }

  @Override
  public void update(GameTime time) {
    // Do nothing
  }

  @Override
  public void reciveMessage(ComponentMessage message, Object args) {
    if (message == ComponentMessage.KILL) {
      owner.addActions(actions);
    }
  }

  @Override
  public ComponentType getComponentType() {
    return ComponentType.ACTION_ON_DEATH;
  }

  @Override
  public void setOwner(IEntity owner) {
    this.owner = owner;
  }
}
