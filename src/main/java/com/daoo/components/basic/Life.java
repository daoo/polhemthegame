package com.daoo.components.basic;

import com.daoo.components.ComponentMessages;
import com.daoo.components.interfaces.IComp;
import com.daoo.entities.Entity;

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
