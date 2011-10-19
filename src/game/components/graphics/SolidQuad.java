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

  public SolidQuad(Color color, int width, int height) {
    this.color  = color;
    this.width  = width;
    this.height = height;
  }

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
    return ComponentType.GRAPHIC;
  }

  @Override
  public void setOwner(IEntity owner) {
    // Do nothing
  }

  @Override
  public void render(Graphics g) {
    g.setColor(color);
    g.fillRect(0, 0, width, height);
  }

}
