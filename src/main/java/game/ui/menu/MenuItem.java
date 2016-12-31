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

  private final int x;
  private final int y;

  private final Image[] stateMap;

  private Image current;
  private MenuItemState state;
  private final Runnable click;

  public MenuItem(String name, int x, int y, Runnable click) throws IOException, ParserException {
    assert click != null;

    this.x = x;
    this.y = y;

    Image imgNormal = CacheTool.getImage(Locator.getCache(), getImagePath(name));
    Image imgActive = CacheTool.getImage(Locator.getCache(), getImagePath(name + "m"));
    Image imgDisabled = imgNormal.copy();

    // Make disabled gray
    imgDisabled.setAlpha(0.5f);
    setImageColor(imgDisabled, COLOR_DISABLED);

    stateMap = new Image[3];
    stateMap[MenuItemState.NORMAL.ordinal()] = imgNormal;
    stateMap[MenuItemState.ACTIVE.ordinal()] = imgActive;
    stateMap[MenuItemState.DISABLED.ordinal()] = imgDisabled;

    state = MenuItemState.NORMAL;
    current = imgNormal;
    this.click = click;
  }

  void render(Graphics g) {
    g.drawImage(current, x, y);
  }

  public void setState(MenuItemState state) {
    current = stateMap[state.ordinal()];
    this.state = state;
  }

  public void click() {
    click.run();
  }

  public MenuItemState getState() {
    return state;
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
