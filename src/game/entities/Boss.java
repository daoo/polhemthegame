/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.entities;

import game.components.holdables.IArmed;
import game.components.holdables.weapons.Weapon;
import game.components.interfaces.ICompAnim;

public class Boss extends Unit implements IArmed {

  public Boss(float x, float y, float width, float height, float dx, float dy, int maxHP, ICompAnim walk, ICompAnim death) {
    super(x, y, width, height, dx, dy, maxHP, walk, death);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void giveWeapon(Weapon weapon) {
    // TODO Auto-generated method stub
    
  }
}
