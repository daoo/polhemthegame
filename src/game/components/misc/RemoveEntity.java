package game.components.misc;

import game.entities.IEntity;
import game.world.World;
import math.time.GameTime;

public class RemoveEntity implements IAction {
  private final IEntity entity;
  public RemoveEntity(final IEntity entity) {
    this.entity = entity;
  }

  @Override
  public void execute(final GameTime time, final World world) {
    world.remove(entity);
  }
}
