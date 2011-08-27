package events;

import entities.Unit;

public class EntityEventArgs extends EventArgs {
  private final Unit unit;

  public EntityEventArgs(final Unit unit) {
    this.unit = unit;
  }

  public Unit getUnit() {
    return unit;
  }
}
