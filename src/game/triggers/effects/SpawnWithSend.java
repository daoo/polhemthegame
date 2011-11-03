/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects;

import game.components.ComponentMessage;
import game.entities.IEntity;
import game.time.GameTime;
import game.triggers.IEffect;
import game.world.World;

public class SpawnWithSend implements IEffect {
  private final IEntity entity;
  private final ComponentMessage message;
  private final Object args;

  public SpawnWithSend(IEntity entity, ComponentMessage message, Object args) {
    this.entity  = entity;
    this.message = message;
    this.args    = args;
  }

  @Override
  public void execute(GameTime time, World world) {
    entity.sendMessage(message, args);
    world.addLast(entity);
  }
}
