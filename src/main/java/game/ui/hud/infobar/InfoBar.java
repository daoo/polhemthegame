/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.ui.hud.infobar;

import org.newdawn.slick.Graphics;

import java.util.ArrayList;

import game.entities.EntityImpl;
import game.ui.DynamicUIElement;


/**
 * Info bar for entities. Can display valuable information (as bars) next to a
 * entity, e.g. remaining health or reload time for the active weapon.
 */
public class InfoBar implements DynamicUIElement {
  private final EntityImpl mEntity;
  private final int mBarWidth;
  private final int mBarHeight;
  private final int mOffsetX;
  private final int mOffsetY;
  private final ArrayList<Bar> mBars;

  /**
   * Creates a new InfoBar.
   *
   * @param entity the entity this info bar is attached to
   * @param barWidth the width of each individual bar
   * @param barHeight the height of each individual bar
   * @param offsetX translation on the x axis before rendering
   * @param offsetY translation on the y axis before rendering
   */
  public InfoBar(EntityImpl entity, int barWidth, int barHeight, int offsetX, int offsetY) {
    mEntity = entity;
    mOffsetX = offsetX;
    mOffsetY = offsetY;

    mBarWidth = barWidth;
    mBarHeight = barHeight;

    mBars = new ArrayList<>();
  }

  /**
   * Add a bar to this InfoBar.
   *
   * @param bar the bar to add, can not be null
   */
  public void add(Bar bar) {
    assert bar != null;

    mBars.add(bar);
  }

  /**
   * Update the data of all bars.
   */
  @Override
  public void update() {
    for (Bar b : mBars) {
      b.update();
    }
  }

  /**
   * Render all bars in the supplied graphics context.
   *
   * @param g the graphics context to use for rendering
   */
  @Override
  public void render(Graphics g) {
    g.pushTransform();
    g.translate(mEntity.getBody().getMin().x, mEntity.getBody().getMin().y);

    int y = mOffsetY;
    for (Bar b : mBars) {
      b.render(g, mOffsetX, y, mBarWidth, mBarHeight);
      y += mBarHeight;
    }

    g.popTransform();
  }

  /**
   * Check if this UI component active.
   * Depends on whether the associated entity is active or not.
   *
   * @return true or false depending on if this is active or not
   */
  @Override
  public boolean isActive() {
    return mEntity.isActive();
  }
}
