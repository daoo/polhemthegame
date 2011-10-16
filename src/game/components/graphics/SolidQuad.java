package game.components.graphics;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.interfaces.IRenderComponent;
import game.entities.IEntity;
import math.time.GameTime;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class SolidQuad implements IRenderComponent {
  private final Color color;
  private final int width, height;

  public SolidQuad(final Color color, final int width, final int height) {
    this.color  = color;
    this.width  = width;
    this.height = height;
  }

  @Override
  public void update(final GameTime time) {
    // Do nothing
  }

  @Override
  public void reciveMessage(final ComponentMessage message, final Object args) {
    // Do nothing
  }

  @Override
  public ComponentType getComponentType() {
    return ComponentType.GRAPHIC;
  }

  @Override
  public void setOwner(final IEntity owner) {
    // Do nothing
  }

  @Override
  public void render(final Graphics g) {
    g.setColor(color);
    g.fillRect(0, 0, width, height);
  }

}
