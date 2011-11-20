/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package ui.hud.infobar;

import game.entities.IEntity;

import java.util.LinkedList;

import org.newdawn.slick.Graphics;

import ui.IDynamicUIElement;

public class InfoBar implements IDynamicUIElement {
  private final IEntity entity;
  private final float barWidth, barHeight;
  private final int offsetX, offsetY;
  private final LinkedList<Bar> bars;

  public InfoBar(IEntity entity, float barWidth, float barHeight, int offsetX, int offsetY) {
    this.entity  = entity;
    this.offsetX = offsetX;
    this.offsetY = offsetY;

    this.barWidth  = barWidth;
    this.barHeight = barHeight;

    bars = new LinkedList<Bar>();
  }

  public void add(Bar bar) {
    bars.add(bar);
  }

  @Override
  public void update() {
    for (Bar b : bars) {
      b.update();
    }
  }

  @Override
  public void render(Graphics g) {
    g.pushTransform();
    g.translate(entity.getBody().getX1(), entity.getBody().getY1());

    int y = offsetY;
    for (Bar b : bars) {
      b.render(g, offsetX, y, barWidth, barHeight);
      y += barHeight;
    }

    g.popTransform();
  }

  @Override
  public boolean isActive() {
    return entity.isActive();
  }
}
