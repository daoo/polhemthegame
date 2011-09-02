/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package com.daoo.ui.menu;

import java.util.HashMap;

import org.newdawn.slick.Graphics;

public class Menus {
  private final HashMap<String, Menu> menus;
  private Menu                        currentMenu;
  private String                      currentName;

  public Menus() {
    menus = new HashMap<String, Menu>(0);
    currentMenu = null;
    currentName = "";
  }

  public Menu current() {
    return currentMenu;
  }

  public void add(final String name, final Menu menu) {
    menus.put(name, menu);
  }

  public void up() {
    currentMenu.up();
  }

  public void down() {
    currentMenu.down();
  }

  public void render(final Graphics g) {
    currentMenu.render(g);
  }

  public void swapTo(final String name) {
    if (menus.containsKey(name)) {
      currentName = name;
      currentMenu = menus.get(currentName);
    }
  }
}
