/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.triggers.condition;

import game.entities.Entity;
import game.pods.GameTime;
import game.triggers.ICondition;
import game.world.World;

import java.util.List;

public class AnyUnactiveCondition implements ICondition {
  private final List<Entity> entities;

  public AnyUnactiveCondition(List<Entity> entities) {
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
