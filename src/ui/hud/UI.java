/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package ui.hud;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import ui.IDynamicUIElement;
import ui.IStaticUIElement;
import ui.IUI;

/**
 * Simple UI implementation.
 */
public class UI implements IUI {
  private final ArrayList<IStaticUIElement> statics;
  private final ArrayList<IDynamicUIElement> dynamics;

  private final ArrayList<IDynamicUIElement> toAdd;

  private final int width, height;

  private int imgX, imgY;
  private Image foreground;

  public UI(int width, int height) {
    this.width  = width;
    this.height = height;

    dynamics = new ArrayList<>();
    statics  = new ArrayList<>();
    toAdd    = new ArrayList<>();

    foreground = null;
  }

  /**
   * Add a static UI element.
   * @param element the element to add, can not be null
   */
  @Override
  public void addStatic(IStaticUIElement element) {
    assert element != null;

    statics.add(element);
  }

  /**
   * Add a dynamic UI element.
   * @param element the element to add, can not be null
   */
  @Override
  public void addDynamic(IDynamicUIElement element) {
    assert element != null;

    toAdd.add(element);
  }

  /**
   * Updates dynamic elements. Also removes inactive elements.
   */
  @Override
  public void update() {
    Iterator<IDynamicUIElement> it = dynamics.iterator();
    while (it.hasNext()) {
      IDynamicUIElement e = it.next();
      if (e.isActive()) {
        e.update();
      } else {
        it.remove();
      }
    }

    dynamics.addAll(toAdd);
    toAdd.clear();
  }

  /**
   * Render dynamics. For example the info bars, these should be relative to the
   * level.
   * @param g the graphics context to use
   */
  @Override
  public void renderDynamics(Graphics g) {
    for (IDynamicUIElement e : dynamics) {
      e.render(g);
    }
  }

  /**
   * Render statics. For example shop menu, these should be relative to the
   * window instead of the level.
   * @param g the rendering context to use
   */
  @Override
  public void renderStatics(Graphics g) {
    for (IStaticUIElement e : statics) {
      e.render(g);
    }

    if (foreground != null) {
      g.drawImage(foreground, imgX, imgY);
    }
  }

  /**
   * Sets an image that is rendered in center of the the foreground. That is
   * above everything else. If image is null the foreground will be cleared.
   * @param image the image to render, if null no image will be rendered.
   */
  @Override
  public void setForegroundImage(Image image) {
    foreground = image;

    if (image != null) {
      imgX = (width - image.getWidth()) / 2;
      imgY = (height - image.getHeight()) / 2;
    }
  }
}
