/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.graphics.animations.IAnimator;
import game.components.graphics.animations.Idle;
import game.components.interfaces.ICompAnim;
import game.entities.interfaces.IEntity;
import math.time.GameTime;

import org.newdawn.slick.Graphics;


public class DummyAnimation implements ICompAnim {
  private final IAnimator animator;

  @Override
  public void update(GameTime time) {
    // Do nothing
  }

  public DummyAnimation() {
    animator = new Idle();
  }

  @Override
  public void render(final Graphics g) {
    // Do nothing
  }

  @Override
  public void goToFirstFrame() {
    // Do nothing
  }

  @Override
  public void setAnimator(final IAnimator animator) {
    // Do nothing
  }

  @Override
  public int getTileWidth() {
    return 0;
  }

  @Override
  public int getTileHeight() {
    return 0;
  }

  @Override
  public Tile getTileCount() {
    return Tile.ZERO;
  }

  @Override
  public Tile getLastTile() {
    return Tile.ZERO;
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
}
