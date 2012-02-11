/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics.debug;

import game.components.ai.BossAI;
import game.components.ai.IBossState;
import game.components.ai.Walking;
import game.components.interfaces.IRenderComponent;
import game.types.GameTime;
import game.types.Message;
import math.Rectangle;
import math.Vector2;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

public class DebugBoss implements IRenderComponent {
  private final Rectangle body;
  private final BossAI ai;

  public DebugBoss(Rectangle body, BossAI ai) {
    this.body = body;
    this.ai   = ai;
  }

  @Override
  public int getWidth() {
    return body.getWidth();
  }

  @Override
  public int getHeight() {
    return body.getHeight();
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
    IBossState state = ai.getState();
    if (state instanceof Walking) {
      Walking walking = (Walking) state;
      Vector2 target = Vector2.subtract(walking.getTarget(), body.getMin());
      g.drawLine(0, 0, target.x, target.y);
    }

    g.draw(new Circle(0, 0, BossAI.MIN_WALK));

    Vector2 o = Vector2.subtract(ai.getMovementRect().getMin(), body.getMin());
    g.drawRect(o.x, o.y, ai.getMovementRect().getWidth(), ai.getMovementRect().getHeight());
  }
}
