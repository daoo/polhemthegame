/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.triggers.condition;

import game.entities.Entity;
import game.pods.GameTime;
import game.triggers.ICondition;
import game.world.World;

public class AllUnactiveCondition implements ICondition {
  private final Iterable<Entity> entities;

  public AllUnactiveCondition(Iterable<Entity> entities) {
    this.entities = entities;
  }

  @Override
  public boolean evaluate(GameTime time, World world) {
    for (Entity entity : entities) {
      if (entity.isActive()) {
        return false;
      }
    }

    return true;
  }
}
