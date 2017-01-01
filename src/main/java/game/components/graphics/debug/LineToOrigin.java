/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics.debug;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import game.components.IRenderComponent;
import game.entities.Entity;
import game.types.GameTime;
import game.types.Message;

public class LineToOrigin implements IRenderComponent {
  private final Entity mOwner;

  public LineToOrigin(Entity owner) {
    mOwner = owner;
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
    g.setColor(Color.red);
    g.drawLine(0, 0, -mOwner.getBody().getMin().x, -mOwner.getBody().getMin().y);
  }
}
