/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package loader.data.json;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;

import loader.data.json.types.ShopItemData;

@SuppressWarnings("InstanceVariableNamingConvention")
public class ShopData implements Closeable {
  public ArrayList<ShopItemData> items;

  @Override
  public void close() throws IOException {
    // No data needs clean up
  }
}
