package game.actions;

import game.components.ComponentMessage;
import game.entities.IEntity;
import game.world.World;
import math.time.GameTime;

public class SpawnWithSend implements IAction {
  private final IEntity entity;
  private final ComponentMessage message;
  private final Object args;

  public SpawnWithSend(IEntity entity, ComponentMessage message, Object args) {
    this.entity  = entity;
    this.message = message;
    this.args    = args;
  }

  @Override
  public void execute(GameTime time, World world) {
    entity.sendMessage(message, args);
    world.add(entity);
  }
}
