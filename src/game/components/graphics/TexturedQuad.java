/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.graphics.animations.IAnimator;
import game.components.interfaces.ICompAnim;
import game.entities.IEntity;
import math.time.GameTime;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;


public class TexturedQuad implements ICompAnim {
  private final Image img;

  public TexturedQuad(Image img) {
    this.img = img;
  }

  @Override
  public void update(GameTime time) {
    // Do nothing
  }

  @Override
  public void render(Graphics g) {
    g.drawImage(img, 0, 0);
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
  public int getTileWidth() {
    return img.getWidth();
  }

  @Override
  public int getTileHeight() {
    return img.getHeight();
  }

  @Override
  public Tile getTileCount() {
    return new Tile(1, 1);
  }

  @Override
  public Tile getLastTile() {
    return Tile.ZERO;
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
  public void setAnimator(IAnimator animator) {
    // Do nothing
  }
}
