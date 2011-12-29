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

public class AllDeadCondition implements ICondition {
  private final Iterable<Entity> entities;

  public AllDeadCondition(Iterable<Entity> entities) {
    this.entities = entities;
  }

  @Override
  public boolean evaluate(GameTime time, World world) {
    for (Entity e : entities) {
      Life life = (Life) e.getComponent(ComponentType.HEALTH);
      if (life.isAlive()) {
        return false;
      }
    }

    return true;
  }
}
