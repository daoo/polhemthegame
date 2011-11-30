/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
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

  private final IEntity owner;

  public Life(IEntity owner, float maxHP) {
    this.owner = owner;
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
      Damage data = (Damage) args;
      damage(data.ammount);
    }
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
