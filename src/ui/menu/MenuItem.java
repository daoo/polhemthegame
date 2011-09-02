/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package ui.menu;

import java.io.IOException;

import loader.parser.ParserException;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import ptg.CacheTool;
import ptg.Locator;


public class MenuItem {
  private static final Color COLOR_DISABLED = Color.gray;

  private final int          x, y;
  private Image              current;
  private final Image        imgNormal, imgActive, imgDisabled;

  private MENU_ITEM_STATE    state;

  public MenuItem(final String name, final int x, final int y)
    throws IOException, ParserException {
    this.x = x;
    this.y = y;

    imgNormal = CacheTool.getImage(Locator.getCache(), "textures/menu/" + name + ".png");
    imgActive = CacheTool.getImage(Locator.getCache(), "textures/menu/" + name + "m.png");
    imgDisabled = imgNormal.copy();

    // Make disabled gray
    imgDisabled.setAlpha(0.5f);
    imgDisabled.setColor(Image.TOP_LEFT, COLOR_DISABLED.r, COLOR_DISABLED.g, COLOR_DISABLED.b, COLOR_DISABLED.a);
    imgDisabled.setColor(Image.TOP_RIGHT, COLOR_DISABLED.r, COLOR_DISABLED.g, COLOR_DISABLED.b, COLOR_DISABLED.a);
    imgDisabled.setColor(Image.BOTTOM_RIGHT, COLOR_DISABLED.r, COLOR_DISABLED.g, COLOR_DISABLED.b, COLOR_DISABLED.a);
    imgDisabled.setColor(Image.BOTTOM_LEFT, COLOR_DISABLED.r, COLOR_DISABLED.g, COLOR_DISABLED.b, COLOR_DISABLED.a);

    current = imgNormal;
    state = MENU_ITEM_STATE.NORMAL;
  }

  void render(final Graphics g) {
    g.drawImage(current, x, y);
  }

  public void setState(final MENU_ITEM_STATE state) {
    if (state == MENU_ITEM_STATE.ACTIVE) {
      current = imgActive;
    } else if (state == MENU_ITEM_STATE.DISABLED) {
      current = imgDisabled;
    } else if (state == MENU_ITEM_STATE.NORMAL) {
      current = imgNormal;
    }

    this.state = state;
  }

  public MENU_ITEM_STATE getState() {
    return state;
  }
}
