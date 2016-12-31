/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.misc;

import game.components.ILogicComponent;
import game.entities.IEntity;
import game.types.Damage;
import game.types.GameTime;
import game.types.Message;
import game.ui.hud.infobar.IProgress;

public class Life implements ILogicComponent, IProgress {
  private boolean mAlive;

  private float mHp;
  private final float mMaxHp;

  private final IEntity mOwner;

  public Life(IEntity owner, float maxHP) {
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
      IEntity sender = null;
      if (args instanceof IEntity) {
        sender = (IEntity) args;
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
  private void kill(IEntity killer) {
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
