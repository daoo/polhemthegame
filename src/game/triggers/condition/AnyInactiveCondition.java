/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.triggers.condition;

import game.entities.Entity;
import game.triggers.ICondition;
import game.types.GameTime;
import game.world.World;

import java.util.List;

public class AnyInactiveCondition implements ICondition {
  private final List<Entity> entities;

  public AnyInactiveCondition(List<Entity> entities) {
    this.entities = entities;
  }

  @Override
  public boolean evaluate(GameTime time, World world) {
    for (Entity entity : entities) {
      if (!entity.isActive()) {
        return true;
      }
    }

    return false;
  }
}
