/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package loader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import loader.parser.IParser;
import loader.parser.ParserException;

public class Cache implements ICache {
  private final FileBeacon beacon;
  private final HashMap<String, IData> cache;

  public Cache(File rootDir) throws IOException {
    assert rootDir != null;

    beacon = new FileBeacon(rootDir);
    cache = new HashMap<>();
  }

  @Override
  public void close() throws IOException {
    beacon.close();

    for (IData cacheItem : cache.values()) {
      cacheItem.close();
    }

    cache.clear();
  }

  @Override
  public IData get(String id, IParser parser)
    throws IOException, ParserException {
    assert id != null;
    assert parser != null;

    IData data = cache.get(id);
    if (data == null) {
      data = readAndParse(id, parser);
      cache.put(id, data);
    }

    return data;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (IData c : cache.values()) {
      sb.append(c);
    }

    return sb.toString();
  }

  private IData readAndParse(String id, IParser parser)
      throws IOException, ParserException {
    assert id != null;
    assert parser != null;

    IData result = null;
    try (InputStream is = beacon.getReader(id)) {
      result = parser.parse(is);
    } catch (ParserException ex) {
      throw new ParserException("Error parsing: " + id, ex);
    }

    return result;
  }
}
