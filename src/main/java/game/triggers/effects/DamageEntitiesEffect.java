/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects;

import java.util.Collection;

import game.course.World;
import game.entities.IEntity;
import game.triggers.IEffect;
import game.types.Damage;
import game.types.GameTime;
import game.types.Message;

public class DamageEntitiesEffect implements IEffect {
  private final Collection<? extends IEntity> mEntities;
  private final Damage mDamage;

  public DamageEntitiesEffect(Collection<? extends IEntity> entities, int damage) {
    mEntities = entities;
    mDamage = new Damage(null, damage);
  }

  @Override
  public void execute(GameTime time, World world) {
    for (IEntity entity : mEntities) {
      entity.sendMessage(Message.DAMAGE, mDamage);
    }
  }
}
