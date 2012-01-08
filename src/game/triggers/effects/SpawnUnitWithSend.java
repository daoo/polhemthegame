/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects;

import game.components.Message;
import game.entities.IEntity;
import game.pods.GameTime;
import game.triggers.IEffect;
import game.world.World;

public class SpawnUnitWithSend implements IEffect {
  private final IEntity entity;
  private final Message message;
  private final Object args;

  public SpawnUnitWithSend(IEntity entity, Message message, Object args) {
    assert entity != null;

    this.entity  = entity;
    this.message = message;
    this.args    = args;
  }

  @Override
  public void execute(GameTime time, World world) {
    entity.sendMessage(message, args);
    world.addUnit(entity);
  }
}
