/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.misc;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import game.components.holdables.weapons.Weapon;
import game.factories.WeaponFactory;
import game.types.Orientation;
import game.types.Wallet;
import loader.data.json.ShopData;
import loader.data.json.types.ShopItemData;
import loader.parser.ParserException;

public class Shop {
  private static class ShopItem {
    private boolean bought;
    private final int price;
    private final Image icon;
    private final Image iconGray;
    private final Weapon weapon;

    private ShopItem(int price, Image icon, Image iconGray, Weapon weapon) {
      this.bought = false;
      this.price = price;
      this.icon = icon;
      this.iconGray = iconGray;
      this.weapon = weapon;
    }
  }

  private final ArrayList<ShopItem> items;
  private final Iterator<ShopItem> it;
  private ShopItem mNext;

  public Shop(ShopData shop, Orientation orientation, WeaponFactory factory) throws ParserException,
      IOException {
    items = new ArrayList<>();

    for (ShopItemData item : shop.items) {
      Image icon = CacheTool.getImage(Locator.getCache(), item.icon);
      Image iconGray = CacheTool.getImage(Locator.getCache(), item.iconGray);

      // These weapons are for players, hence right orientation
      Weapon weapon = factory.makeWeapon(item.weapon, orientation);

      ShopItem tmp = new ShopItem(item.price, icon, iconGray, weapon);
      items.add(tmp);
    }

    it = items.iterator();
    mNext = it.next();
  }

  public void render(Graphics g, int spacing) {
    int x = spacing;
    for (ShopItem item : items) {
      g.drawImage(item.bought ? item.icon : item.iconGray, x, spacing);
      x += spacing + item.icon.getWidth();
    }
  }

  public boolean canAffordNext(Wallet wallet) {
    return mNext != null && wallet.getMoney() >= mNext.price;
  }

  public Weapon buyNext(Wallet wallet) {
    if (mNext == null) {
      return null;
    }

    if (wallet.takeMoney(mNext.price)) {
      mNext.bought = true;
      Weapon tmp = mNext.weapon;

      mNext = it.hasNext() ? it.next() : null;

      return tmp;
    }

    return null;
  }
}
