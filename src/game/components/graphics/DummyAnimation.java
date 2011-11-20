/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.graphics.animations.IAnimator;
import game.components.graphics.animations.Idle;
import game.components.graphics.animations.Tile;
import game.components.interfaces.IAnimatedComponent;
import game.time.GameTime;

import org.newdawn.slick.Graphics;

public class DummyAnimation implements IAnimatedComponent {
  public DummyAnimation() {
    // Do nothing
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
    return Tile.ZERO;
  }

  @Override
  public int getTileHeight() {
    return 0;
  }

  @Override
  public int getTileWidth() {
    return 0;
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
    // Do nothing
  }

  @Override
  public void setAnimator(IAnimator animator) {
    // Do nothing
  }

  @Override
  public void update(GameTime time) {
    // Do nothing
  }
}
