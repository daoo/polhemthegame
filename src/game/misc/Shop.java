/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package game.misc;

import game.components.holdables.weapons.Weapon;

import java.util.Iterator;
import java.util.LinkedList;

import loader.data.json.ShopData;
import loader.data.json.WeaponsData.WeaponData;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Shop {
  private class ShopItem {
    public boolean bought;
    public final int price;
    public final Image icon;
    public final WeaponData weapon;

    public ShopItem(int price, Image icon, WeaponData weapon) {
      this.bought = false;
      this.price  = price;
      this.icon   = icon;
      this.weapon = weapon;
    }
  }

  private final LinkedList<ShopItem> items;
  private final Iterator<ShopItem> buyer;
  private ShopItem next;

  public Shop(ShopData shop) {
    items = new LinkedList<Shop.ShopItem>();

    for (ShopData.ShopItemData item : shop.items) {
      ShopItem tmp = new ShopItem(item.price, null, null);
      items.add(tmp);
    }

    buyer = items.iterator();
    next  = buyer.next();
  }

  public void render(Graphics g, int spacing) {
    int x = spacing;
    for (ShopItem item : items) {
      g.drawRect(x, spacing, 100, 100);
      x += spacing;
    }
  }

  public boolean canAffordNext(Wallet wallet) {
    return next != null && wallet.getMoney() >= next.price;
  }

  public Weapon buyNext(Wallet wallet) {
    if (next == null)
      return;

    if (wallet.takeMoney(next.price)) {
      next.bought = true;
      if (buyer.hasNext()) {
        next = buyer.next();
      } else {
        next = null;
      }

      // TODO: Create weapon
      return null;
    }

    return null;
  }
}
