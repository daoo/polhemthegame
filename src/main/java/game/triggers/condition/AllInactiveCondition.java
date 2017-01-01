/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.triggers.condition;

import game.course.World;
import game.entities.Entity;
import game.triggers.Condition;
import game.types.GameTime;

public class AllInactiveCondition implements Condition {
  private final Iterable<? extends Entity> mEntities;

  public AllInactiveCondition(Iterable<? extends Entity> entities) {
    mEntities = entities;
  }

  @Override
  public boolean evaluate(GameTime time, World world) {
    for (Entity entity : mEntities) {
      if (entity.isActive()) {
        return false;
      }
    }

    return true;
  }
}
