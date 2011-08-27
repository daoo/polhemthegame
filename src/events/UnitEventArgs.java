package events;

import entities.Unit;

public class UnitEventArgs implements IEventArgs {
  private final Unit unit;

  public UnitEventArgs(final Unit unit) {
    this.unit = unit;
  }

  public Unit getUnit() {
    return unit;
  }
}
