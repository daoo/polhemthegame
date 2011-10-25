package game.triggers.condition;

import game.components.ComponentType;
import game.components.life.Life;
import game.entities.EntityType;
import game.entities.IEntity;
import game.time.GameTime;
import game.triggers.ICondition;
import game.world.World;

public class AnyPlayerDeadCondition implements ICondition {
  @Override
  public boolean evaluate(GameTime time, World world) {
    for (IEntity player : world.get(EntityType.PLAYER)) {
      Life life = (Life) player.getComponent(ComponentType.HEALTH);
      if (!life.isAlive()) {
        return true;
      }
    }

    return false;
  }
}
