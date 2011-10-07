/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.entities;

import game.components.holdables.IArmed;
import game.components.holdables.weapons.Weapon;
import game.components.interfaces.ICompAnim;

public class Boss extends Unit implements IArmed {

  public Boss(final float x, final float y, final float width, final float height, final float dx, final float dy, final int maxHP, final ICompAnim walk, final ICompAnim death) {
    super(x, y, width, height, dx, dy, maxHP, walk, death);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void giveWeapon(final Weapon weapon) {
    // TODO Auto-generated method stub

  }
}
