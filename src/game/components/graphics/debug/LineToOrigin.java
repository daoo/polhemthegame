package game.components.graphics.debug;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.interfaces.IRenderComponent;
import game.entities.IEntity;
import game.time.GameTime;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class LineToOrigin implements IRenderComponent {
  private IEntity owner;

  @Override
  public void update(GameTime time) {
    // Do nothing
  }

  @Override
  public void reciveMessage(ComponentMessage message, Object args) {
    // Do nothing
  }

  @Override
  public ComponentType getComponentType() {
    return ComponentType.LINE_TO_ORIGIN;
  }

  @Override
  public void setOwner(IEntity owner) {
    this.owner = owner;
  }

  @Override
  public void render(Graphics g) {
    g.setColor(Color.red);
    g.drawLine(0, 0, -owner.getBody().getX1(), -owner.getBody().getY1());
  }
}
