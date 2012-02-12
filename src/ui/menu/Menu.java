/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package ui.menu;

import java.util.ArrayList;
import java.util.Collection;

import org.newdawn.slick.Graphics;

public class Menu {
  private final ArrayList<MenuItem> items;

  private int i;
  private MenuItem current;

  public Menu(Collection<MenuItem> items) {
    this.items = new ArrayList<>(items);

    i = 0;
    current = this.items.get(i);
    current.setState(MenuItemState.ACTIVE);
  }

  public void click() {
    if (current instanceof MenuButton) {
      ((MenuButton) current).click();
    }
  }

  public void render(Graphics g) {
    for (MenuItem i : items) {
      i.render(g);
    }
  }

  public void up() {
    while (i - 1 >= 0) {
      current.setState(MenuItemState.NORMAL);
      --i;
      current = items.get(i);

      if (current.getState() == MenuItemState.NORMAL) {
        current.setState(MenuItemState.ACTIVE);
        return;
      }
    }
  }

  public void down() {
    while (i + 1 < items.size()) {
      current.setState(MenuItemState.NORMAL);
      ++i;
      current = items.get(i);

      if (current.getState() == MenuItemState.NORMAL) {
        current.setState(MenuItemState.ACTIVE);
        return;
      }
    }
  }
}
