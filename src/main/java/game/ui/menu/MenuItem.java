/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.ui.menu;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import java.io.IOException;

import game.misc.CacheTool;
import game.misc.Locator;
import loader.parser.ParserException;

public class MenuItem {
  private static final Color COLOR_DISABLED = Color.gray;

  private final int mX;
  private final int mY;

  private final Image[] mStateMap;

  private Image mCurrent;
  private MenuItemState mState;
  private final Runnable mClick;

  public MenuItem(String name, int x, int y, Runnable click) throws IOException, ParserException {
    assert click != null;

    mX = x;
    mY = y;

    Image imgNormal = CacheTool.getImage(Locator.getCache(), getImagePath(name));
    Image imgActive = CacheTool.getImage(Locator.getCache(), getImagePath(name + "m"));
    Image imgDisabled = imgNormal.copy();

    // Make disabled gray
    imgDisabled.setAlpha(0.5f);
    setImageColor(imgDisabled, COLOR_DISABLED);

    mStateMap = new Image[3];
    mStateMap[MenuItemState.NORMAL.ordinal()] = imgNormal;
    mStateMap[MenuItemState.ACTIVE.ordinal()] = imgActive;
    mStateMap[MenuItemState.DISABLED.ordinal()] = imgDisabled;

    mState = MenuItemState.NORMAL;
    mCurrent = imgNormal;
    mClick = click;
  }

  void render(Graphics g) {
    g.drawImage(mCurrent, mX, mY);
  }

  public void setState(MenuItemState state) {
    mCurrent = mStateMap[state.ordinal()];
    mState = state;
  }

  public void click() {
    mClick.run();
  }

  public MenuItemState getState() {
    return mState;
  }

  private static String getImagePath(String name) {
    return "textures/menu/" + name + ".png";
  }

  private static void setImageColor(Image img, Color c) {
    img.setColor(Image.TOP_LEFT, c.r, c.g, c.b, c.a);
    img.setColor(Image.TOP_RIGHT, c.r, c.g, c.b, c.a);
    img.setColor(Image.BOTTOM_RIGHT, c.r, c.g, c.b, c.a);
    img.setColor(Image.BOTTOM_LEFT, c.r, c.g, c.b, c.a);
  }
}
