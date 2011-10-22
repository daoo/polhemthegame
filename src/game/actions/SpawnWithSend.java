/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.actions;

import game.components.ComponentMessage;
import game.entities.IEntity;
import game.time.GameTime;
import game.world.World;

public class SpawnWithSend implements IAction {
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
    world.add(entity);
  }
}
