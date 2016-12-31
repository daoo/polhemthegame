/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.ui.menu;

import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.Collection;

public class Menu {
  private final ArrayList<MenuItem> items;

  private int currentIndex;
  private MenuItem currentItem;

  public Menu(Collection<MenuItem> items) {
    this.items = new ArrayList<>(items);

    currentIndex = 0;
    currentItem = this.items.get(currentIndex);
    currentItem.setState(MenuItemState.ACTIVE);
  }

  public void click() {
    currentItem.click();
  }

  public void render(Graphics g) {
    for (MenuItem item : items) {
      item.render(g);
    }
  }

  public void up() {
    while (currentIndex - 1 >= 0) {
      currentItem.setState(MenuItemState.NORMAL);
      --currentIndex;
      currentItem = items.get(currentIndex);

      if (currentItem.getState() == MenuItemState.NORMAL) {
        currentItem.setState(MenuItemState.ACTIVE);
        return;
      }
    }
  }

  public void down() {
    while (currentIndex + 1 < items.size()) {
      currentItem.setState(MenuItemState.NORMAL);
      ++currentIndex;
      currentItem = items.get(currentIndex);

      if (currentItem.getState() == MenuItemState.NORMAL) {
        currentItem.setState(MenuItemState.ACTIVE);
        return;
      }
    }
  }
}
