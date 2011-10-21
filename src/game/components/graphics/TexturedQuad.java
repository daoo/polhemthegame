/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.graphics.animations.IAnimator;
import game.components.graphics.animations.Idle;
import game.components.interfaces.IAnimatedComponent;
import game.entities.IEntity;
import game.time.GameTime;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;


public class TexturedQuad implements IAnimatedComponent {
  private final Image img;

  public TexturedQuad(Image img) {
    this.img = img;
  }

  @Override
  public IAnimator getAnimator() {
    return new Idle();
  }

  @Override
  public ComponentType getComponentType() {
    return ComponentType.GRAPHIC;
  }

  @Override
  public Tile getLastTile() {
    return Tile.ZERO;
  }

  @Override
  public Tile getTileCount() {
    return new Tile(1, 1);
  }

  @Override
  public int getTileHeight() {
    return img.getHeight();
  }

  @Override
  public int getTileWidth() {
    return img.getWidth();
  }

  @Override
  public void goToFirstFrame() {
    // Do nothing
  }

  @Override
  public void reciveMessage(ComponentMessage message, Object args) {
    // Do nothing
  }

  @Override
  public void render(Graphics g) {
    g.drawImage(img, 0, 0);
  }

  @Override
  public void setAnimator(IAnimator animator) {
    // Do nothing
  }

  @Override
  public void setOwner(IEntity owner) {
    // Do nothing
  }

  @Override
  public void update(GameTime time) {
    // Do nothing
  }
}
