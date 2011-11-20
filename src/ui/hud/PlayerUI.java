/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package ui.hud;

import game.components.ComponentType;
import game.components.misc.Inventory;
import game.entities.IEntity;
import game.misc.Shop;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import ui.IStaticUIElement;

public class PlayerUI implements IStaticUIElement {
  public static final int HEIGHT = 75;
  public static final int WIDTH  = 1024;

  private static final int PADDING = 10;

  private static final String MONEY = "Money: ";
  private static final String KILLS = "Kills: ";

  private final int x, y;

  private final int stats_x, stats_y;
  private final int kills_y;

  private final Inventory inventory;
  private final Shop shop;

  public PlayerUI(int x, int y, IEntity player, Shop shop) {
    this.x = x;
    this.y = y;

    this.shop = shop;
    inventory = (Inventory) player.getComponent(ComponentType.INVENTORY);

    stats_x = WIDTH - 100 - PADDING;
    stats_y = PADDING;
    kills_y = stats_y + 25 + PADDING;
  }

  @Override
  public void render(Graphics g) {
    g.pushTransform();
    g.translate(x, y);

    g.setColor(Color.white);
    g.drawRect(0, 0, WIDTH, HEIGHT);

    shop.render(g, PADDING);

    String mStr = MONEY + String.valueOf(inventory.getWallet().getMoney());
    String kStr = KILLS + String.valueOf(0);

    g.drawString(mStr, stats_x, stats_y);
    g.drawString(kStr, stats_x, kills_y);

    g.popTransform();
  }
}
