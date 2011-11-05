/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
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

  public InfoBar(IEntity entity, float width, float height, int offsetX, int offsetY) {
    this.entity  = entity;
    this.offsetX = offsetX;
    this.offsetY = offsetY;

    barWidth  = width;
    barHeight = height;

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

    int i = 0;
    for (Bar b : bars) {
      b.render(g, offsetX, offsetY + (barHeight * i), barWidth, barHeight);
      ++i;
    }

    g.popTransform();
  }

  @Override
  public boolean isActive() {
    return entity.isActive();
  }
}
