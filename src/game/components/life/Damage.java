package game.components.life;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.interfaces.ILogicComponent;
import game.entities.IEntity;
import math.time.GameTime;

public class Damage {
  public final float ammount;
  public final IEntity source;

  public Damage(IEntity source, float ammount) {
    this.source  = source;
    this.ammount = ammount;
  }
}
