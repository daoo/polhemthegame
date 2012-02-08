/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.holdables.weapons;

public class InfiniteMagazine implements IMagazine {
  public InfiniteMagazine() {
  }

  @Override
  public void takeOne() throws OutOfAmmoException {
    // Do nothing
  }

  @Override
  public void reload() {
    // Do nothing
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public float getFractionFilled() {
    return 1;
  }
}
