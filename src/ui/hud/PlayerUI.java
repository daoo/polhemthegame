/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package ui.hud;

import game.components.misc.Inventory;
import game.misc.Shop;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import ui.IStaticUIElement;

public class PlayerUI implements IStaticUIElement {
  public static final int HEIGHT = 75;
  public static final int WIDTH  = 1024;

  private static final int STATS_WIDTH       = 100;
  private static final int STATS_LINE_HEIGHT = 25;
  private static final int PADDING           = 10;

  private static final String MONEY = "Money: ";
  private static final String KILLS = "Kills: ";

  private final int x, y;

  private final int stats_x, stats_y;
  private final int kills_y;

  private final Inventory inventory;
  private final Shop shop;

  public PlayerUI(int x, int y, Shop shop, Inventory inv) {
    this.x = x;
    this.y = y;

    this.shop = shop;
    this.inventory = inv;

    stats_x = WIDTH - STATS_WIDTH - PADDING;
    stats_y = PADDING;
    kills_y = stats_y + STATS_LINE_HEIGHT + PADDING;
  }

  @Override
  public void render(Graphics g) {
    g.pushTransform();
    g.translate(x, y);

    g.setColor(Color.black);
    g.fillRect(0, 0, WIDTH, HEIGHT);

    g.setColor(Color.white);
    g.drawRect(0, 0, WIDTH, HEIGHT);

    shop.render(g, PADDING);

    String mStr = MONEY + String.valueOf(inventory.getWallet().getMoney());
    String kStr = KILLS + String.valueOf(inventory.getKills());

    g.drawString(mStr, stats_x, stats_y);
    g.drawString(kStr, stats_x, kills_y);

    g.popTransform();
  }
}
