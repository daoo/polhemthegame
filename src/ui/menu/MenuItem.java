/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package ui.menu;

import java.io.IOException;

import loader.parser.ParserException;
import main.Launcher;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import other.CacheTool;

// TODO: Disabled menu items
public class MenuItem {
  protected final String name;
  protected final int    x, y;
  protected Image        current;
  protected final Image  iNormal, iActive;
  protected final Color  colorDisabled = Color.gray;

  MENU_ITEM_STATE        state;

  public MenuItem(final String name, final int x, final int y,
                  final boolean disabled) throws IOException, ParserException {
    this.name = name;

    this.x = x;
    this.y = y;

    iNormal = CacheTool.getImage(Launcher.cache, "textures/menu/" + name + ".png");
    iActive = CacheTool.getImage(Launcher.cache, "textures/menu/" + name + "m.png");

    current = iNormal;

    if (disabled) {
      state = MENU_ITEM_STATE.DISABLED;
    } else {
      state = MENU_ITEM_STATE.NORMAL;
    }
  }

  void render(final Graphics g) {
    g.drawImage(current, x, y);
  }

  public void setState(final MENU_ITEM_STATE state) {
    if (state == MENU_ITEM_STATE.ACTIVE) {
      current = iActive;
    } else if (state == MENU_ITEM_STATE.DISABLED) {
      current = iNormal;
    } else if (state == MENU_ITEM_STATE.NORMAL) {
      current = iNormal;
    }
  }

  public MENU_ITEM_STATE getState() {
    return state;
  }
}
