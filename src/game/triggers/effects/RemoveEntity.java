/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects;

import game.entities.IEntity;
import game.time.GameTime;
import game.triggers.IEffect;
import game.world.World;

public class RemoveEntity implements IEffect {
  private final IEntity entity;

  public RemoveEntity(IEntity entity) {
    this.entity = entity;
  }

  @Override
  public void execute(GameTime time, World world) {
    entity.remove();
  }
}
