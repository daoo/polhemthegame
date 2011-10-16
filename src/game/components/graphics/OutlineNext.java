package game.components.graphics;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.interfaces.IRenderComponent;
import game.components.physics.Movement;
import game.entities.IEntity;
import math.Rectangle;
import math.time.GameTime;

import org.newdawn.slick.Graphics;

public class OutlineNext implements IRenderComponent {
  private IEntity owner;
  private Movement movement;

  public OutlineNext() {
    // Do nothing
  }

  @Override
  public ComponentType getComponentType() {
    return ComponentType.GRAPHIC;
  }

  @Override
  public void reciveMessage(final ComponentMessage message, final Object args) {
    // Do nothing
  }

  @Override
  public void render(final Graphics g) {
    final Rectangle body = owner.getBody();

    final Rectangle rect = new Rectangle(body);
    rect.addPosition(movement.getVelocity());

    g.drawRect(rect.getX1(), rect.getY1(), rect.getWidth(), rect.getHeight());
    g.drawLine(body.getCenter().x, body.getCenter().y,
               rect.getCenter().x, rect.getCenter().y);
  }

  @Override
  public void setOwner(final IEntity owner) {
    this.owner = owner;
    this.movement = (Movement) owner.getComponent(ComponentType.MOVEMENT);
  }

  @Override
  public void update(final GameTime time) {
    // Do nothing
  }
}
