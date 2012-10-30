/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics.debug;

import game.components.IRenderComponent;
import game.components.physics.Movement;
import game.entities.Entity;
import game.types.GameTime;
import game.types.Message;
import math.Rectangle;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Outliner implements IRenderComponent {
  private static final Color FIRST  = Color.red;
  private static final Color SECOND = Color.green;
  private static final Color THIRD  = Color.blue;

  private final Entity owner;
  private final Movement movement;

  private final boolean outlineNext;
  private final boolean linesToNext;

  public Outliner(Entity owner, Movement movement, boolean outlineNext,
      boolean lineToNext) {
    this.owner    = owner;
    this.movement = movement;

    this.outlineNext = outlineNext;
    this.linesToNext = lineToNext;
  }

  @Override
  public int getWidth() {
    return owner.body.getWidth();
  }

  @Override
  public int getHeight() {
    return owner.body.getHeight();
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    // Do nothing
  }

  @Override
  public void render(Graphics g) {
    Rectangle body = owner.body;

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
