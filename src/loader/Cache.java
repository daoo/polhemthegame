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
  private final HashMap<String, Closeable> cache;

  public Cache(File rootDir) throws FileNotFoundException {
    assert rootDir != null;

    beacon = new FileBeacon(rootDir);
    cache = new HashMap<>();
  }

  @Override
  public void close() throws IOException {
    for (Closeable cacheItem : cache.values()) {
      cacheItem.close();
    }

    cache.clear();
  }

  @Override
  public int count() {
    return cache.size();
  }

  @Override
  public void delete(String id) throws IOException {
    assert id != null;

    cache.get(id).close();
    cache.remove(id);
  }

  @Override
  public Closeable getCold(String id, IParser parser)
    throws IOException, ParserException {
    assert id != null;
    assert parser != null;

    Closeable data;
    if (cache.containsKey(id)) {
      data = cache.get(id);
    } else {
      data = readAndParse(id, parser);
      cache.put(id, data);
    }

    return data;
  }

  @Override
  public Closeable getWarm(String id) throws ObjectNotInCacheException {
    assert id != null;

    Closeable c = cache.get(id);
    if (c != null) {
      return c;
    }

    throw new ObjectNotInCacheException(id);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Closeable c : cache.values()) {
      sb.append(c);
    }

    return sb.toString();
  }

  private Closeable readAndParse(String id, IParser parser)
      throws IOException, ParserException {
    assert id != null;
    assert parser != null;

    Closeable result = null;
    try (InputStream is = beacon.getReader(id)) {
      result = parser.parse(is);
    } catch (ParserException ex) {
      throw new ParserException("Error parsing: " + id, ex);
    }

    return result;
  }
}
