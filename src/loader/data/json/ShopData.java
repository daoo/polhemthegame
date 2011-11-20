/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package loader.data.json;

import java.io.Closeable;
import java.io.IOException;
import java.util.LinkedList;

public class ShopData implements Closeable {
  public class ShopItemData {
    public String weapon;
    public int    price;
    public String icon;
    public String iconGray;
  }

  public LinkedList<ShopItemData> items;

  @Override
  public void close() throws IOException {
    // No data needs clean up
  }
}
