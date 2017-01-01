/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.misc;

import game.components.LogicComponent;
import game.entities.Entity;
import game.types.Damage;
import game.types.GameTime;
import game.types.Message;
import game.ui.hud.infobar.Progressing;

public class Life implements LogicComponent, Progressing {
  private boolean mAlive;

  private float mHp;
  private final float mMaxHp;

  private final Entity mOwner;

  public Life(Entity owner, float maxHP) {
    mOwner = owner;
    mAlive = true;
    mHp = maxHP;
    mMaxHp = maxHP;
  }

  @Override
  public float getProgress() {
    return mHp / mMaxHp;
  }

  public boolean isAlive() {
    return mAlive;
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    if (message == Message.KILL) {
      Entity sender = null;
      if (args instanceof Entity) {
        sender = (Entity) args;
      }

      kill(sender);
    } else if (message == Message.DAMAGE) {
      damage((Damage) args);
    }
  }

  @Override
  public void update(GameTime time) {
    // Do nothing
  }

  /**
   * Deal some damage to this. Also kills if the hp goes to zero or less using
   * the supplied damage source as killer.
   *
   * @param dmg information about the damage
   */
  private void damage(Damage dmg) {
    if (mAlive) {
      mHp -= dmg.ammount;
      if (mHp <= 0) {
        kill(dmg.source);
      }
    }
  }

  /**
   * Kills the entity that owns this component.
   * Sends a KILLED_ENTITY message to the killer. If killer is null, no message
   * is sent but the owner is still killed. Nothing happens if we have been
   * killed before.
   *
   * @param killer the killer to whom the KILLED_ENTITY message is sent, can be
   * null.
   */
  private void kill(Entity killer) {
    if (mAlive) {
      mHp = 0;
      mAlive = false;
      mOwner.remove();

      mOwner.sendMessage(Message.KILL, null);

      if (killer != null) {
        killer.sendMessage(Message.KILLED_ENTITY, mOwner);
      }
    }
  }

  @Override
  public String toString() {
    return "Life - " + mHp + "/" + mMaxHp + " (" + getProgress() + ")";
  }
}
