/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package loader;

import java.io.Closeable;
import java.io.IOException;

class CacheItem {
  private final String    id;
  private final Closeable data;

  public CacheItem(String id, Closeable data) {
    this.data = data;
    this.id = id;
  }

  public Closeable getData() {
    return data;
  }

  public void close() throws IOException {
    data.close();
  }

  @Override
  public String toString() {
    return String.format("Object %s: %s", id, data.toString());
  }
}
