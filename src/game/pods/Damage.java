package game.pods;

import game.entities.IEntity;

public class Damage {
  public final float ammount;
  public final IEntity source;

  public Damage(IEntity source, float ammount) {
    this.source  = source;
    this.ammount = ammount;
  }
}
