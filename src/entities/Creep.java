/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package entities;

import org.newdawn.slick.SlickException;

import components.interfaces.ICompAnim;
import components.interfaces.IUnit;

public class Creep extends Unit implements IUnit {
  private final int moneyGain;
  private final float spawnTime, damage;

  public Creep(final float x, final float y,
               final float width, final float height, final float ang,
               final float speed,
               final int moneyGain, final float damage,
               final int maxHP, final float spawnTime,
               final ICompAnim walk, final ICompAnim death)
    throws SlickException {
    super(x, y, width, height,
          (float) Math.cos(ang) * speed,
          (float) Math.sin(ang) * speed,
          maxHP, walk, death);

    this.spawnTime = spawnTime;

    this.moneyGain = moneyGain;
    this.damage = damage;

    add(walk);
  }

  public float getSpawnTime() {
    return spawnTime;
  }

  public int getMoneyGain() {
    return moneyGain;
  }

  public float getDamage() {
    return damage;
  }
}
