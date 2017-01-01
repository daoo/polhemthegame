/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics.debug;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

import game.components.IRenderComponent;
import game.components.ai.BossAI;
import game.components.ai.IBossState;
import game.components.ai.Walking;
import game.types.GameTime;
import game.types.Message;
import math.Aabb;
import math.Vector2;

public class DebugBoss implements IRenderComponent {
  private final Aabb mBody;
  private final BossAI mAi;

  public DebugBoss(Aabb body, BossAI ai) {
    mBody = body;
    mAi = ai;
  }

  @Override
  public void update(GameTime time) {
    // Do nothing
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    // Do nothing
  }

  @Override
  public void render(Graphics g) {
    IBossState state = mAi.getState();
    if (state instanceof Walking) {
      Walking walking = (Walking) state;
      Vector2 target = Vector2.subtract(walking.getTarget(), mBody.getMin());
      g.drawLine(0, 0, target.x, target.y);
    }

    g.draw(new Circle(0, 0, BossAI.MIN_WALK));

    Vector2 o = Vector2.subtract(mAi.getMovementRect().getMin(), mBody.getMin());
    g.drawRect(o.x, o.y, mAi.getMovementRect().getSize().x, mAi.getMovementRect().getSize().y);
  }
}
