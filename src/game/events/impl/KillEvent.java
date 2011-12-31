package game.events.impl;

import game.components.Message;
import game.entities.IEntity;
import game.events.IEvent;

public class KillEvent implements IEvent<ObjectEventArgs> {
  @Override
  public void execute(IEntity sender, ObjectEventArgs args) {
    args.getObject().sendMessage(Message.KILL, sender);
  }
}
