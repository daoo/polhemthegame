/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics.debug;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import game.components.IRenderComponent;
import game.components.physics.Movement;
import game.entities.Entity;
import game.types.GameTime;
import game.types.Message;
import math.Rectangle;

public class Outliner implements IRenderComponent {
  private static final Color FIRST = Color.red;
  private static final Color SECOND = Color.green;
  private static final Color THIRD = Color.blue;

  private final Entity mOwner;
  private final Movement mMovement;

  private final boolean mOutlineNext;
  private final boolean mLinesToNext;

  public Outliner(Entity owner, Movement movement, boolean outlineNext, boolean lineToNext) {
    mOwner = owner;
    mMovement = movement;

    mOutlineNext = outlineNext;
    mLinesToNext = lineToNext;
  }

  @Override
  public int getWidth() {
    return mOwner.getBody().getWidth();
  }

  @Override
  public int getHeight() {
    return mOwner.getBody().getHeight();
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    // Do nothing
  }

  @Override
  public void render(Graphics g) {
    Rectangle body = mOwner.getBody();

    Color tmp = g.getColor();
    g.setColor(FIRST);
    g.drawRect(0, 0, body.getWidth(), body.getHeight());

    if (mMovement != null) {
      float dx = mMovement.getVelocity().x;
      float dy = mMovement.getVelocity().y;

      if (mOutlineNext) {
        g.setColor(SECOND);
        g.drawRect(dx, dy, body.getWidth(), body.getHeight());
      }

      if (mLinesToNext) {
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