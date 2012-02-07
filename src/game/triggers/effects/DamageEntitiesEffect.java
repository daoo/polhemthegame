/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects;

import game.components.Message;
import game.entities.IEntity;
import game.triggers.IEffect;
import game.types.Damage;
import game.types.GameTime;
import game.world.World;

import java.util.Collection;

public class DamageEntitiesEffect implements IEffect {
  private final Collection<? extends IEntity> entities;
  private final Damage damage;

  public DamageEntitiesEffect(Collection<? extends IEntity> entities, int damage) {
    this.entities = entities;
    this.damage = new Damage(null, damage);
  }

  @Override
  public void execute(GameTime time, World world) {
    for (IEntity entity : entities) {
      entity.sendMessage(Message.DAMAGE, damage);
    }
  }
}
