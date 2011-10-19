/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.misc;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.interfaces.ILogicComponent;
import game.entities.IEntity;
import math.time.GameTime;
import ui.hud.infobar.IProgress;

public class Life implements ILogicComponent, IProgress {
  private boolean alive;

  private float hp;
  private final float maxHP;

  private IEntity owner;

  public Life(final float maxHP) {
    this.alive = true;

    this.hp    = maxHP;
    this.maxHP = maxHP;
  }

  @Override
  public ComponentType getComponentType() {
    return ComponentType.HEALTH;
  }

  @Override
  public float getProgress() {
    return hp / maxHP;
  }

  public boolean isAlive() {
    return alive;
  }

  @Override
  public void reciveMessage(final ComponentMessage message, final Object args) {
    if (message == ComponentMessage.KILL) {
      hp    = 0;
      alive = false;
    } else if (message == ComponentMessage.DAMAGE) {
      // Type hack, the compiler will kill me
      if (args instanceof Damage) {
        damage(((Damage) args).getAmmount());
      }
    }
  }

  @Override
  public void setOwner(final IEntity owner) {
    this.owner = owner;
  }

  @Override
  public void update(final GameTime time) {
    // Do nothing
  }

  private void damage(final float dmg) {
    hp -= dmg;
    if (hp <= 0) {
      kill();
    }
  }

  private void kill() {
    owner.sendMessage(ComponentMessage.KILL, null);
  }
}
