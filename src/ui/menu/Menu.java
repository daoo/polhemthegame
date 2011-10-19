/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package ui.menu;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

public class Menu {
  private final ArrayList<MenuItem> items;
  private int                       current;

  public Menu() {
    items = new ArrayList<MenuItem>(0);
    current = 0;
  }

  public void click() {
    if (items.get(current) instanceof MenuButton) {
      ((MenuButton) items.get(current)).click();
    }
  }

  public void add(MenuItem item) {
    if (items.isEmpty()) {
      current = 0;
      item.setState(MENU_ITEM_STATE.ACTIVE);
    }

    items.add(item);
  }

  public void render(Graphics g) {
    for (MenuItem i : items) {
      i.render(g);
    }
  }

  public void up() {
    int i = current - 1;
    while (i >= 0 && items.get(i).getState() != MENU_ITEM_STATE.NORMAL) {
      --i;
    }

    if (i > -1) {
      items.get(current).setState(MENU_ITEM_STATE.NORMAL);
      current = i;
      items.get(current).setState(MENU_ITEM_STATE.ACTIVE);
    }
  }

  public void down() {
    int i = current + 1;
    while (i < items.size() && items.get(i).getState() != MENU_ITEM_STATE.NORMAL) {
      ++i;
    }

    if (i < items.size()) {
      items.get(current).setState(MENU_ITEM_STATE.NORMAL);
      current = i;
      items.get(current).setState(MENU_ITEM_STATE.ACTIVE);
    }
  }
}
