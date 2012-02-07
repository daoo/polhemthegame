/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects;

import game.entities.IEntity;
import game.triggers.IEffect;
import game.types.GameTime;
import game.world.World;

public class RemoveEntityEffect implements IEffect {
  private final IEntity entity;

  public RemoveEntityEffect(IEntity entity) {
    this.entity = entity;
  }

  @Override
  public void execute(GameTime time, World world) {
    entity.remove();
  }
}
