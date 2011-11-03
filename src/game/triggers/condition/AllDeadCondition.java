/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package game.triggers.condition;

import game.components.ComponentType;
import game.components.life.Life;
import game.entities.IEntity;
import game.time.GameTime;
import game.triggers.ICondition;
import game.world.World;

public class AllDeadCondition implements ICondition {
  private final Iterable<IEntity> entities;

  public AllDeadCondition(Iterable<IEntity> entities) {
    this.entities = entities;
  }

  @Override
  public boolean evaluate(GameTime time, World world) {
    for (IEntity e : entities) {
      Life life = (Life) e.getComponent(ComponentType.HEALTH);
      if (life.isAlive()) {
        return false;
      }
    }

    return true;
  }
}
