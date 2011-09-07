/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package components.basic;

import components.ComponentMessages;
import components.interfaces.IComp;

import entities.Entity;

public class Life implements IComp {
  private final Entity owner;

  private boolean alive;

  private final float maxHP;
  private float hp;

  public Life(final Entity owner, float maxHP) {
    this.owner = owner;

    this.alive = true;

    this.hp = maxHP;
    this.maxHP = maxHP;
  }

  public void kill() {
    hp = 0;
    alive = false;
    owner.sendMessage(ComponentMessages.KILLED);
  }

  public void damage(final float dmg) {
    hp -= dmg;
    if (hp <= 0) {
      kill();
    }
  }

  @Override
  public void reciveMessage(ComponentMessages message) {
    // Do nothing
  }

  public boolean isAlive() {
    return alive;
  }

  public float getHPFraction() {
    return hp / maxHP;
  }
}
