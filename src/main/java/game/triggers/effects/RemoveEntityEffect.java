/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects;

import game.course.World;
import game.entities.IEntity;
import game.triggers.IEffect;
import game.types.GameTime;

public class RemoveEntityEffect implements IEffect {
  private final IEntity mEntity;

  public RemoveEntityEffect(IEntity entity) {
    mEntity = entity;
  }

  @Override
  public void execute(GameTime time, World world) {
    mEntity.remove();
  }
}
