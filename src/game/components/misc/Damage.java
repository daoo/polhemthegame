package game.components.misc;

import math.time.GameTime;
import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.interfaces.ILogicComponent;
import game.entities.IEntity;

public class Damage implements ILogicComponent {
  private final float ammount;

  public Damage(final float ammount) {
    this.ammount = ammount;
  }

  public float getAmmount() {
    return ammount;
  }

  @Override
  public void update(GameTime time) {
    // Do nothing
  }

  @Override
  public void reciveMessage(ComponentMessage message, Object args) {
    // Do nothing
  }

  @Override
  public ComponentType getComponentType() {
    return ComponentType.DAMAGE;
  }

  @Override
  public void setOwner(IEntity owner) {
    // Do nothing
  }
}