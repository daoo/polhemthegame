/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.actions;

import game.entities.IEntity;
import game.time.GameTime;
import game.world.World;

public class RemoveEntity implements IAction {
  private final IEntity entity;

  public RemoveEntity(IEntity entity) {
    this.entity = entity;
  }

  @Override
  public void execute(GameTime time, World world) {
    world.remove(entity);
  }
}
