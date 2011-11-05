/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.life;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.interfaces.ILogicComponent;
import game.entities.IEntity;
import game.pods.Damage;
import game.time.GameTime;
import ui.hud.infobar.IProgress;

public class Life implements ILogicComponent, IProgress {
  private boolean alive;

  private float hp;
  private final float maxHP;

  private IEntity owner;

  public Life(float maxHP) {
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
  public void reciveMessage(ComponentMessage message, Object args) {
    if (message == ComponentMessage.KILL) {
      hp    = 0;
      alive = false;
      owner.remove();
    } else if (message == ComponentMessage.DAMAGE) {
      // Type hack, the compiler will kill me
      if (args instanceof Damage) {
        damage(((Damage) args).ammount);
      }
    }
  }

  @Override
  public void setOwner(IEntity owner) {
    this.owner = owner;
  }

  @Override
  public void update(GameTime time) {
    // Do nothing
  }

  private void damage(float dmg) {
    hp -= dmg;
    if (hp <= 0) {
      kill();
    }
  }

  private void kill() {
    owner.sendMessage(ComponentMessage.KILL, null);
  }
}
