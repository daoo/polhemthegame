/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.triggers.condition;

import game.components.ComponentType;
import game.components.misc.Life;
import game.entities.Entity;
import game.pods.GameTime;
import game.triggers.ICondition;
import game.world.World;

import java.util.List;

public class AnyDeadCondition implements ICondition {
  private final List<Entity> entities;

  public AnyDeadCondition(List<Entity> entities) {
    this.entities = entities;
  }

  @Override
  public boolean evaluate(GameTime time, World world) {
    for (Entity entity : entities) {
      Life life = (Life) entity.getComponent(ComponentType.HEALTH);
      if (!life.isAlive()) {
        return true;
      }
    }

    return false;
  }
}
