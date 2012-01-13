package game.components.graphics.debug;

import game.components.Message;
import game.components.ai.BossAI;
import game.components.ai.IBossState;
import game.components.ai.Walking;
import game.components.interfaces.IRenderComponent;
import game.pods.GameTime;
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
      Vector2 target = walking.getTarget();
      if (target != null) {
        Vector2 a = Vector2.subtract(target, body.getCenter());
        g.drawLine(body.getHalfSize().x, body.getHalfSize().y, a.x, a.y);
      }
    }

    g.draw(new Circle(body.getHalfSize().x, body.getHalfSize().y, BossAI.MIN_WALK));
  }
}
