/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package ui.hud.infobar;

import game.entities.Entity;

import java.util.LinkedList;

import org.newdawn.slick.Graphics;

import ui.IDynamicUIElement;

/**
 * Info bar for entities. Can display valuable information (as bars) next to a
 * entity, e.g. remaining health or reload time for the active weapon.
 */
public class InfoBar implements IDynamicUIElement {
  private final Entity entity;
  private final int barWidth, barHeight;
  private final int offsetX, offsetY;
  private final LinkedList<Bar> bars;

  /**
   * Creates a new InfoBar.
   * @param entity the entity this info bar is attached to
   * @param barWidth the width of each individual bar
   * @param barHeight the height of each individual bar
   * @param offsetX translation on the x axis before rendering
   * @param offsetY translation on the y axis before rendering
   */
  public InfoBar(Entity entity, int barWidth, int barHeight, int offsetX, int offsetY) {
    this.entity  = entity;
    this.offsetX = offsetX;
    this.offsetY = offsetY;

    this.barWidth  = barWidth;
    this.barHeight = barHeight;

    bars = new LinkedList<>();
  }

  /**
   * Add a bar to this InfoBar.
   * @param bar the bar to add, can not be null
   */
  public void add(Bar bar) {
    assert bar != null;

    bars.add(bar);
  }

  /**
   * Update the data of all bars.
   */
  @Override
  public void update() {
    for (Bar b : bars) {
      b.update();
    }
  }

  /**
   * Render all bars in the supplied graphics context.
   * @param g the graphics context to use for rendering
   */
  @Override
  public void render(Graphics g) {
    g.pushTransform();
    g.translate(entity.body.getX1(), entity.body.getY1());

    int y = offsetY;
    for (Bar b : bars) {
      b.render(g, offsetX, y, barWidth, barHeight);
      y += barHeight;
    }

    g.popTransform();
  }

  /**
   * Check if this UI component active.
   * Depends on whether the associated entity is active or not.
   * @return true or false depending on if this is active or not
   */
  @Override
  public boolean isActive() {
    return entity.isActive();
  }
}
