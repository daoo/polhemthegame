/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.misc;

import game.components.Message;
import game.components.interfaces.ILogicComponent;
import game.entities.Entity;
import game.pods.GameTime;
import game.pods.TimePos;
import math.Vector2;

public class RangeLimiter implements ILogicComponent {
  private final Entity owner;

  private TimePos start;

  private final float duration;
  private final float range;

  public RangeLimiter(Entity owner, float duration, float range) {
    this.owner = owner;

    this.duration = duration;
    this.range    = range;
  }

  @Override
  public void update(GameTime time) {
    if ((time.elapsed - start.time) > duration) {
      owner.sendMessage(Message.KILL, null);
    }
    if (Vector2.distance(owner.body.getMin(), start.pos) > range) {
      owner.sendMessage(Message.KILL, null);
    }
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    if (message == Message.START_AT) {
      start = (TimePos) args;
    }
  }
}
