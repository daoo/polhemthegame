/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.misc;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.interfaces.ILogicComponent;
import game.entities.IEntity;
import game.pods.GameTime;
import game.pods.TimePos;

public class RangeLimiter implements ILogicComponent {
  private final IEntity owner;

  private TimePos start;

  private final float duration;
  private final float range;

  public RangeLimiter(IEntity owner, float duration, float range) {
    this.owner = owner;

    this.duration = duration;
    this.range    = range;
  }

  @Override
  public void update(GameTime time) {
    if ((time.elapsed - start.time) > duration) {
      owner.sendMessage(ComponentMessage.KILL, null);
    }
    if (owner.getBody().getMin().distance(start.pos) > range) {
      owner.sendMessage(ComponentMessage.KILL, null);
    }
  }

  @Override
  public void reciveMessage(ComponentMessage message, Object args) {
    if (message == ComponentMessage.START_AT) {
      start = (TimePos) args;
    }
  }

  @Override
  public ComponentType getComponentType() {
    return ComponentType.RANGE_LIMITER;
  }
}
