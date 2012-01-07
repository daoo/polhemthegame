/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics.debug;

import game.components.Message;
import game.components.interfaces.IRenderComponent;
import game.components.physics.Movement;
import game.entities.IEntity;
import game.pods.GameTime;
import math.Rectangle;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Outliner implements IRenderComponent {
  private static final Color FIRST  = Color.red;
  private static final Color SECOND = Color.green;
  private static final Color THIRD  = Color.blue;

  private final IEntity owner;
  private final Movement movement;

  private final boolean outlineNext;
  private final boolean linesToNext;

  public Outliner(IEntity owner, Movement movement, boolean outlineNext, boolean lineToNext) {
    this.owner    = owner;
    this.movement = movement;

    this.outlineNext = outlineNext;
    this.linesToNext = lineToNext;
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    // Do nothing
  }

  @Override
  public void render(Graphics g) {
    Rectangle body = owner.getBody();

    Color tmp = g.getColor();
    g.setColor(FIRST);
    g.drawRect(0, 0, body.getWidth(), body.getHeight());

    if (movement != null) {
      float dx = movement.getVelocity().x;
      float dy = movement.getVelocity().y;

      if (outlineNext) {
        g.setColor(SECOND);
        g.drawRect(dx, dy, body.getWidth(), body.getHeight());
      }

      if (linesToNext) {
        g.setColor(THIRD);

        float x1 = 0;
        float y1 = 0;
        float x2 = body.getWidth();
        float y2 = body.getHeight();

        g.drawLine(x1, y1, x1 + dx, y1 + dy);
        g.drawLine(x2, y1, x2 + dx, y1 + dy);
        g.drawLine(x2, y2, x2 + dx, y2 + dy);
        g.drawLine(x1, y2, x1 + dx, y2 + dy);
      }
    }
    g.setColor(tmp);
  }

  @Override
  public void update(GameTime time) {
    // Do nothing
  }
}
