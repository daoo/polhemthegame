/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects;

import game.course.World;
import game.entities.Entity;
import game.triggers.Effect;
import game.types.GameTime;

public class RemoveEntityEffect implements Effect {
  private final Entity mEntity;

  public RemoveEntityEffect(Entity entity) {
    mEntity = entity;
  }

  @Override
  public void execute(GameTime time, World world) {
    mEntity.remove();
  }
}
