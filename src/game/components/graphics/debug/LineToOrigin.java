/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics.debug;

import game.components.Message;
import game.components.interfaces.IRenderComponent;
import game.entities.Entity;
import game.pods.GameTime;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class LineToOrigin implements IRenderComponent {
  private final Entity owner;

  public LineToOrigin(Entity owner) {
    this.owner = owner;
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
    g.drawLine(0, 0, -owner.body.getX1(), -owner.body.getY1());
  }
}
