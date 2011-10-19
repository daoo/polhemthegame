package game.components.misc;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.interfaces.ILogicComponent;
import game.entities.IEntity;
import math.Vector2;
import math.time.GameTime;

public class RangeLimiter implements ILogicComponent {
  public static class TimePos {
    public final float time;
    public final Vector2 pos;

    public TimePos(float time, Vector2 pos) {
      this.time = time;
      this.pos  = new Vector2(pos);
    }
  }

  private IEntity owner;

  private TimePos start;

  private final float duration;
  private final float range;

  public RangeLimiter(float duration, float range) {
    this.duration = duration;
    this.range    = range;
  }

  @Override
  public void update(GameTime time) {
    if ((time.getElapsed() - start.time) > duration) {
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

  @Override
  public void setOwner(IEntity owner) {
    this.owner = owner;
  }
}
