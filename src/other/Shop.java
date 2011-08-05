/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package other;

import java.util.HashMap;

import loader.data.json.ShopData.ShopItemData;

import components.basic.Inventory;

public class Shop {
  protected final HashMap<String, ShopItemData> items;

  public Shop() {
    items = new HashMap<String, ShopItemData>();
  }

  public ShopItemData buyItem(final String id, final Inventory inv) {
    final ShopItemData item = items.get(id);
    if (inv.takeMoney(item.price)) {
      return item;
    }
    else {
      return null;
    }
  }
}
