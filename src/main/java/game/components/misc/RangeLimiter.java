/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.misc;

import game.components.ILogicComponent;
import game.entities.Entity;
import game.types.GameTime;
import game.types.Message;
import game.types.TimePos;
import math.Vector2;

public class RangeLimiter implements ILogicComponent {
  private final Entity owner;

  private TimePos start;

  private final int duration;
  private final int range;

  public RangeLimiter(Entity owner, int duration, int range) {
    this.owner = owner;

    this.duration = duration;
    this.range    = range;
  }

  @Override
  public void update(GameTime time) {
    if ((time.elapsedMilli - start.time) > duration ||
        Vector2.distance(owner.body.getMin(), start.pos) > range) {
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
