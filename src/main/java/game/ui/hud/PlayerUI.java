/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.ui.hud;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import game.components.misc.Inventory;
import game.misc.Shop;
import game.ui.IStaticUIElement;


public class PlayerUI implements IStaticUIElement {
  public static final int HEIGHT = 75;

  private static final int STATS_WIDTH = 100;
  private static final int STATS_LINE_HEIGHT = 25;
  private static final int PADDING = 10;

  private static final String MONEY = "Money: ";
  private static final String KILLS = "Kills: ";

  private final int mX;
  private final int mY;

  private final int mStatsX;
  private final int mStatsY;
  private final int mKillsY;

  private final Inventory mInventory;
  private final Shop mShop;

  public PlayerUI(int x, int y, int width, Shop shop, Inventory inv) {
    mX = x;
    mY = y;

    mShop = shop;
    mInventory = inv;

    mStatsX = width - STATS_WIDTH - PADDING;
    mStatsY = PADDING;
    mKillsY = mStatsY + STATS_LINE_HEIGHT + PADDING;
  }

  @Override
  public void render(Graphics g) {
    g.pushTransform();
    g.translate(mX, mY);

    mShop.render(g, PADDING);

    String mStr = MONEY + mInventory.getWallet().getMoney();
    String kStr = KILLS + mInventory.getKills();

    g.setColor(Color.white);
    g.drawString(mStr, mStatsX, mStatsY);
    g.drawString(kStr, mStatsX, mKillsY);

    g.popTransform();
  }
}
