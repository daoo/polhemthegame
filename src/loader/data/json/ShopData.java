/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package com.daoo.loader.data.json;

import java.util.ArrayList;

import com.daoo.loader.data.DataException;
import com.daoo.loader.data.IClosable;

public class ShopData implements IClosable {
  public class ShopItemData {
    public String weapon;
    public int    price;
    public String icon;
  }

  public ArrayList<ShopItemData> items;

  @Override
  public void close() throws DataException {
    // No data needs clean up
  }
}
