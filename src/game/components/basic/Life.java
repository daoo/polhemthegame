/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.basic;


import game.components.ComponentMessages;
import game.components.interfaces.IComp;
import game.entities.Entity;

public class Life implements IComp {
  private final Entity owner;

  private boolean alive;

  private final float maxHP;
  private float hp;

  public Life(final Entity owner, float maxHP) {
    this.owner = owner;

    this.alive = true;

    this.hp    = maxHP;
    this.maxHP = maxHP;
  }

  public void kill() {
    owner.sendMessage(ComponentMessages.KILL);
  }

  public void damage(final float dmg) {
    hp -= dmg;
    if (hp <= 0) {
      kill();
    }
  }

  @Override
  public void reciveMessage(final ComponentMessages message) {
    if (message == ComponentMessages.KILL) {
      hp    = 0;
      alive = false;
    }
  }

  public boolean isAlive() {
    return alive;
  }

  public float getHPFraction() {
    return hp / maxHP;
  }
}
