/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package ui.hud;

import game.entities.IEntity;

import org.newdawn.slick.Graphics;

import ui.IUIElement;

public class ShopUI implements IUIElement {
  public static final int HEIGHT = 75;
  public static final int WIDTH  = 1024;

  private static final int PADDING = 5;

  private static final String MONEY   = "Money: ";
  private static final String KILLS   = "Kills: ";

  private final int stats_x, stats_y;
  private final int kills_y;

  private int money, kills;

  public ShopUI(IEntity player) {
    money = 0;
    kills = 0;

    stats_x = WIDTH - 100 - PADDING;
    stats_y = PADDING;
    kills_y = stats_y + 25 + PADDING;
  }

  @Override
  public void render(Graphics g) {
    String mStr = MONEY + String.valueOf(money);
    String kStr = KILLS + String.valueOf(kills);

    g.drawString(mStr, stats_x, stats_y);
    g.drawString(kStr, stats_x, kills_y);
  }

  @Override
  public void update() {
    // Do nothing
  }
}
