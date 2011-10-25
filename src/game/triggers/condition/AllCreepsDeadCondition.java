package game.triggers.condition;

import java.util.Iterator;

import game.entities.EntityType;
import game.entities.IEntity;
import game.time.GameTime;
import game.triggers.ICondition;
import game.world.World;

public class AllCreepsDeadCondition implements ICondition {
  @Override
  public boolean evaluate(GameTime time, World world) {
    Iterator<IEntity> it = world.get(EntityType.CREEP).iterator();
    return !it.hasNext();
  }
}
