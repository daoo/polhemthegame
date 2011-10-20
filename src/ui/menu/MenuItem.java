/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package ui.menu;

import game.CacheTool;

import java.io.IOException;

import loader.parser.ParserException;
import main.Locator;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;



public class MenuItem {
  private static final Color COLOR_DISABLED = Color.gray;

  private final int          x, y;
  private Image              current;
  private final Image        imgNormal, imgActive, imgDisabled;

  private MenuItemState    state;

  public MenuItem(String name, int x, int y)
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
    state = MenuItemState.NORMAL;
  }

  void render(Graphics g) {
    g.drawImage(current, x, y);
  }

  public void setState(MenuItemState state) {
    if (state == MenuItemState.ACTIVE) {
      current = imgActive;
    } else if (state == MenuItemState.DISABLED) {
      current = imgDisabled;
    } else if (state == MenuItemState.NORMAL) {
      current = imgNormal;
    }

    this.state = state;
  }

  public MenuItemState getState() {
    return state;
  }
}
