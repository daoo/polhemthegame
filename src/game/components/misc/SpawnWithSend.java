package game.components.misc;

import game.components.ComponentMessage;
import game.entities.IEntity;
import game.world.World;
import math.time.GameTime;

public class SpawnWithSend implements IAction {
  private final IEntity entity;
  private final ComponentMessage message;
  private final Object args;

  public SpawnWithSend(final IEntity entity, final ComponentMessage message,
                       final Object args) {
    this.entity  = entity;
    this.message = message;
    this.args    = args;
  }

  public void execute(final GameTime time, final World world) {
    entity.sendMessage(message, args);
    world.add(entity);
  }
}
