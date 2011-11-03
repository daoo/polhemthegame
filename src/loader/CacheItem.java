/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package loader;

import loader.data.DataException;
import loader.data.IClosable;

public class CacheItem {
  private final String    id;
  private final IClosable data;

  public CacheItem(String id, IClosable data) {
    this.data = data;
    this.id = id;
  }

  public IClosable getData() {
    return data;
  }

  public void close() throws DataException {
    data.close();
  }

  @Override
  public String toString() {
    return String.format("Object %s: %s", id, data.toString());
  }
}
