/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package ui.hud.infobar;

// TODO: A 1px high bar turns weird color-wise from time to time (probably because of aa)

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.interfaces.IRenderComponent;
import game.entities.IEntity;

import java.util.LinkedList;

import math.time.GameTime;

import org.newdawn.slick.Graphics;

public class InfoBar implements IRenderComponent {
  private final float barWidth, barHeight;
  private final int offsetX, offsetY;
  private final LinkedList<Bar> bars;

  public InfoBar(float width, float height, int offsetX, int offsetY) {
    barWidth = width;
    barHeight = height;

    this.offsetX = offsetX;
    this.offsetY = offsetY;

    bars = new LinkedList<Bar>();
  }

  @Override
  public void update(GameTime time) {
    for (Bar b : bars) {
      b.update();
    }
  }

  @Override
  public void render(Graphics g) {
    int i = 0;
    for (Bar b : bars) {
      b.render(g, offsetX, offsetY + (barHeight * i), barWidth, barHeight);
      ++i;
    }
  }

  public void add(Bar bar) {
    bars.add(bar);
  }

  public float getWidth() {
    return barWidth;
  }

  @Override
  public void reciveMessage(ComponentMessage message, Object args) {
    // Do nothing
  }

  @Override
  public ComponentType getComponentType() {
    return ComponentType.INFO_BAR;
  }

  @Override
  public void setOwner(IEntity owner) {
    // Do nothing
  }
}
