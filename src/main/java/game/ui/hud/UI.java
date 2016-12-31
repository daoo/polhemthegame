/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.ui.hud;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import java.util.ArrayList;
import java.util.Iterator;

import game.ui.IDynamicUIElement;
import game.ui.IStaticUIElement;


/**
 * Simple UI implementation.
 */
public class UI {
  private final ArrayList<IStaticUIElement> mStatics;
  private final ArrayList<IDynamicUIElement> mDynamics;

  private final ArrayList<IDynamicUIElement> mToAdd;

  private final int mWidth;
  private final int mHeight;

  private int mImgX;
  private int mImgY;
  private Image mForeground;

  public UI(int width, int height) {
    mWidth = width;
    mHeight = height;

    mDynamics = new ArrayList<>();
    mStatics = new ArrayList<>();
    mToAdd = new ArrayList<>();

    mForeground = null;
  }

  /**
   * Add a static UI element.
   *
   * @param element the element to add, can not be null
   */
  public void addStatic(IStaticUIElement element) {
    assert element != null;

    mStatics.add(element);
  }

  /**
   * Add a dynamic UI element.
   *
   * @param element the element to add, can not be null
   */
  public void addDynamic(IDynamicUIElement element) {
    assert element != null;

    mToAdd.add(element);
  }

  /**
   * Updates dynamic elements. Also removes inactive elements.
   */
  public void update() {
    Iterator<IDynamicUIElement> it = mDynamics.iterator();
    while (it.hasNext()) {
      IDynamicUIElement e = it.next();
      if (e.isActive()) {
        e.update();
      } else {
        it.remove();
      }
    }

    mDynamics.addAll(mToAdd);
    mToAdd.clear();
  }

  /**
   * Render dynamics. For example the info bars, these should be relative to the
   * level.
   *
   * @param g the graphics context to use
   */
  public void renderDynamics(Graphics g) {
    for (IDynamicUIElement e : mDynamics) {
      e.render(g);
    }
  }

  /**
   * Render statics. For example shop menu, these should be relative to the
   * window instead of the level.
   *
   * @param g the rendering context to use
   */
  public void renderStatics(Graphics g) {
    for (IStaticUIElement e : mStatics) {
      e.render(g);
    }

    if (mForeground != null) {
      g.drawImage(mForeground, mImgX, mImgY);
    }
  }

  /**
   * Sets an image that is rendered in center of the the foreground. That is
   * above everything else. If image is null the foreground will be cleared.
   *
   * @param image the image to render, if null no image will be rendered.
   */
  public void setForegroundImage(Image image) {
    mForeground = image;

    if (image != null) {
      mImgX = (mWidth - image.getWidth()) / 2;
      mImgY = (mHeight - image.getHeight()) / 2;
    }
  }
}
