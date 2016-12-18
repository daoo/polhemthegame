/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.triggers.condition;

import java.util.Arrays;

import game.course.World;
import game.entities.IEntity;
import game.triggers.ICondition;
import game.types.GameTime;

public class AllInactiveCondition implements ICondition {
  private final Iterable<? extends IEntity> entities;

  public AllInactiveCondition(Iterable<? extends IEntity> entities) {
    this.entities = entities;
  }

  public AllInactiveCondition(IEntity entity) {
    this(Arrays.asList(entity));
  }

  @Override
  public boolean evaluate(GameTime time, World world) {
    for (IEntity entity : entities) {
      if (entity.isActive()) {
        return false;
      }
    }

    return true;
  }
}
