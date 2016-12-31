/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.ui.menu;

import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.Collection;

public class Menu {
  private final ArrayList<MenuItem> mItems;

  private int mCurrentIndex;
  private MenuItem mCurrentItem;

  public Menu(Collection<MenuItem> items) {
    mItems = new ArrayList<>(items);

    mCurrentIndex = 0;
    mCurrentItem = mItems.get(mCurrentIndex);
    mCurrentItem.setState(MenuItemState.ACTIVE);
  }

  public void click() {
    mCurrentItem.click();
  }

  public void render(Graphics g) {
    for (MenuItem item : mItems) {
      item.render(g);
    }
  }

  public void up() {
    while (mCurrentIndex - 1 >= 0) {
      mCurrentItem.setState(MenuItemState.NORMAL);
      --mCurrentIndex;
      mCurrentItem = mItems.get(mCurrentIndex);

      if (mCurrentItem.getState() == MenuItemState.NORMAL) {
        mCurrentItem.setState(MenuItemState.ACTIVE);
        return;
      }
    }
  }

  public void down() {
    while (mCurrentIndex + 1 < mItems.size()) {
      mCurrentItem.setState(MenuItemState.NORMAL);
      ++mCurrentIndex;
      mCurrentItem = mItems.get(mCurrentIndex);

      if (mCurrentItem.getState() == MenuItemState.NORMAL) {
        mCurrentItem.setState(MenuItemState.ACTIVE);
        return;
      }
    }
  }
}
