/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.misc;

import game.components.LogicComponent;
import game.entities.Entity;
import game.types.Damage;
import game.types.GameTime;
import game.types.Message;

public class ProjectileDamage implements LogicComponent {
  private final Damage mDamageOther;
  private final Damage mDamageSelf;

  private final Entity mOwner;
  private final Entity mSource;

  public ProjectileDamage(Entity owner, Entity source, float damage) {
    mOwner = owner;
    mSource = source;

    mDamageSelf = new Damage(null, 1);
    mDamageOther = new Damage(source, damage);
  }

  @Override
  public void update(GameTime time) {
    // Do nothing
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    if (message == Message.COLLIDED_WITH) {
      Entity entity = (Entity) args;
      entity.sendMessage(Message.DAMAGE, mDamageOther);
      mOwner.sendMessage(Message.DAMAGE, mDamageSelf);
      mSource.sendMessage(Message.DEALT_DAMAGE, mDamageOther);
    }
  }
}
