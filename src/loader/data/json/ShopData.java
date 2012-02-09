/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package loader.data.json;

import java.io.IOException;
import java.util.ArrayList;

import loader.IData;
import loader.data.json.types.ShopItemData;

public class ShopData implements IData {
  public ArrayList<ShopItemData> items;

  @Override
  public void close() throws IOException {
    // No data needs clean up
  }
}
