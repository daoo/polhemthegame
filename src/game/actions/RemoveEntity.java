package game.actions;

import game.entities.IEntity;
import game.world.World;
import math.time.GameTime;

public class RemoveEntity implements IAction {
  private final IEntity entity;

  public RemoveEntity(IEntity entity) {
    this.entity = entity;
  }

  @Override
  public void execute(GameTime time, World world) {
    world.remove(entity);
  }
}
