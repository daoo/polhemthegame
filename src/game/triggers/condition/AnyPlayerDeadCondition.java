/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.triggers.condition;

import game.components.ComponentType;
import game.components.misc.Life;
import game.entities.IEntity;
import game.pods.GameTime;
import game.triggers.ICondition;
import game.world.World;

import java.util.List;

public class AnyPlayerDeadCondition implements ICondition {
  private final List<IEntity> players;

  public AnyPlayerDeadCondition(List<IEntity> players) {
    this.players = players;
  }

  @Override
  public boolean evaluate(GameTime time, World world) {
    for (IEntity player : players) {
      Life life = (Life) player.getComponent(ComponentType.HEALTH);
      if (!life.isAlive()) {
        return true;
      }
    }

    return false;
  }
}
