/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.triggers.condition;

import game.course.World;
import game.entities.IEntity;
import game.triggers.ICondition;
import game.types.GameTime;

public class AllInactiveCondition implements ICondition {
  private final Iterable<? extends IEntity> mEntities;

  public AllInactiveCondition(Iterable<? extends IEntity> entities) {
    mEntities = entities;
  }

  @Override
  public boolean evaluate(GameTime time, World world) {
    for (IEntity entity : mEntities) {
      if (entity.isActive()) {
        return false;
      }
    }

    return true;
  }
}
