/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package loader.data.json;

import java.util.LinkedList;

import loader.data.DataException;
import loader.data.IClosable;

public class ShopData implements IClosable {
  public class ShopItemData {
    public String weapon;
    public int    price;
    public String icon;
  }

  public LinkedList<ShopItemData> items;

  @Override
  public void close() throws DataException {
    // No data needs clean up
  }
}
