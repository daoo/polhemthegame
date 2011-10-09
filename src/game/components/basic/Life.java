/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.basic;


import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.interfaces.ILogicComponent;
import game.entities.interfaces.IEntity;
import math.time.GameTime;

public class Life implements ILogicComponent {
  private IEntity owner;

  private float hp;
  private boolean alive;

  private final float maxHP;

  public Life(final float maxHP) {
    this.alive = true;

    this.hp    = maxHP;
    this.maxHP = maxHP;
  }

  public void damage(final float dmg) {
    hp -= dmg;
    if (hp <= 0) {
      kill();
    }
  }

  @Override
  public void reciveMessage(final ComponentMessage message, final Object args) {
    if (message == ComponentMessage.KILL) {
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

  private void kill() {
    owner.sendMessage(ComponentMessage.KILL, null);
  }

  @Override
  public void update(GameTime time) {
    // Do nothing
  }

  @Override
  public ComponentType getComponentType() {
    return ComponentType.HEALTH;
  }

  @Override
  public void setOwner(final IEntity owner) {
    this.owner = owner;
  }
}
