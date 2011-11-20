/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package loader;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import loader.parser.IParser;
import loader.parser.ParserException;

public class Cache implements ICache {
  private final FileBeacon beacon;
  private final HashMap<String, CacheItem> cache;

  private Closeable readAndParse(String id, IParser parser)
    throws IOException, ParserException {

    Closeable result = null;
    try (InputStream is = beacon.getReader(id)) {
      result = parser.parse(is);
    } catch (ParserException ex) {
      throw new ParserException("Error parsing: " + id, ex);
    }

    return result;
  }

  public Cache(File rootDir) throws FileNotFoundException {
    beacon = new FileBeacon(rootDir);
    cache = new HashMap<>();
  }

  @Override
  public void close() throws IOException {
    for (CacheItem cacheItem : cache.values()) {
      cacheItem.close();
    }

    cache.clear();
  }

  @Override
  public void delete(String id) throws IOException {
    cache.get(id).close();
    cache.remove(id);
  }

  @Override
  public Closeable getCold(String id, IParser parser)
    throws IOException, ParserException {
    Closeable data;
    if (cache.containsKey(id)) {
      data = cache.get(id).getData();
    } else {
      data = readAndParse(id, parser);
      cache.put(id, new CacheItem(id, data));
    }

    return data;
  }

  @Override
  public Closeable getWarm(String id) throws ObjectNotInCacheException {
    CacheItem c = cache.get(id);
    if (c != null) {
      return c.getData();
    }

    throw new ObjectNotInCacheException(id);
  }

  @Override
  public int count() {
    return cache.size();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (CacheItem c : cache.values()) {
      sb.append(c);
    }

    return sb.toString();
  }
}
