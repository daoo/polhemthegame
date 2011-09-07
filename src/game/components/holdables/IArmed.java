/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.holdables;

import game.components.holdables.weapons.Weapon;

public interface IArmed {
  public void giveWeapon(final Weapon weapon);
}
