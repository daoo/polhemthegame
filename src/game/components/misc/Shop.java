/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.misc;

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
      this.price = price;
      this.icon = icon;
      this.weapon = weapon;
    }
  }

  private final LinkedList<ShopItem> items;
  private final Iterator<ShopItem> buyer;

  public Shop(ShopData shop) {
    items = new LinkedList<Shop.ShopItem>();

    for (ShopData.ShopItemData item : shop.items) {
      ShopItem tmp = new ShopItem(0, null, null);
      items.add(tmp);
    }

    buyer = items.listIterator();
  }

  public void render(Graphics g, int spacing) {
  }

  public boolean canAffordNext(int money) {
    buyer.
  }

  public Weapon buyNext(int money) {
  }
}
