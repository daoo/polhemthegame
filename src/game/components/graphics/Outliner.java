package game.components.graphics;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.interfaces.IRenderComponent;
import game.components.physics.Movement;
import game.entities.IEntity;
import math.Rectangle;
import math.time.GameTime;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Outliner implements IRenderComponent {
  private static final Color FIRST = Color.red;
  private static final Color SECOND = Color.green;

  private IEntity owner;
  private Movement movement;

  private final boolean outlineNext;

  public Outliner(boolean outlineNext) {
    this.outlineNext = outlineNext;
  }

  @Override
  public ComponentType getComponentType() {
    return ComponentType.GRAPHIC;
  }

  @Override
  public void reciveMessage(ComponentMessage message, Object args) {
    // Do nothing
  }

  @Override
  public void render(Graphics g) {
    Rectangle body = owner.getBody();

    Color tmp = g.getColor();
    g.setColor(FIRST);
    g.drawRect(0, 0, body.getWidth(), body.getHeight());

    if (outlineNext && movement != null) {
      g.setColor(SECOND);
      g.drawRect(movement.getVelocity().x, movement.getVelocity().y, body.getWidth(), body.getHeight());
    }
    g.setColor(tmp);
  }

  @Override
  public void setOwner(IEntity owner) {
    this.owner = owner;
    this.movement = (Movement) owner.getComponent(ComponentType.MOVEMENT);
  }

  @Override
  public void update(final GameTime time) {
    // Do nothing
  }
}
