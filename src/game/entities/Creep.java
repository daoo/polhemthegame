/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.entities;

import game.components.interfaces.ICompAnim;
import game.entities.groups.EntityType;

public class Creep extends Unit {
  private final int moneyGain;
  private final float damage;

  public Creep(final float x, final float y,
               final float width, final float height, final float ang,
               final float speed, final int moneyGain, final float damage,
               final int maxHP, final ICompAnim walk, final ICompAnim death) {
    super(x, y, width, height,
          (float) Math.cos(ang) * speed,
          (float) Math.sin(ang) * speed,
          EntityType.CREEP,
          maxHP, walk, death);

    this.moneyGain = moneyGain;
    this.damage = damage;
  }

  public int getMoneyGain() {
    return moneyGain;
  }

  public float getDamage() {
    return damage;
  }
}
