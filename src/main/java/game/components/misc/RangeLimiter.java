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
  private final Entity mOwner;

  private TimePos mStart;

  private final int mDuration;
  private final int mRange;

  public RangeLimiter(Entity owner, int duration, int range) {
    mOwner = owner;

    mDuration = duration;
    mRange = range;
  }

  @Override
  public void update(GameTime time) {
    if (time.elapsedMilli - mStart.time > mDuration ||
        Vector2.distance(mOwner.getBody().getMin(), mStart.pos) > mRange) {
      mOwner.sendMessage(Message.KILL, null);
    }
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    if (message == Message.START_AT) {
      mStart = (TimePos) args;
    }
  }
}
