/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.holdables.weapons;

public class InfiniteMagazine implements Magazine {

  @Override
  public boolean takeOne() {
    return true;
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
