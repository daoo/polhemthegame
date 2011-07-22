/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package loader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import loader.data.IClosable;
import loader.parser.IParser;
import loader.parser.ParserException;

public class Cache {
  private final FileBeacon                 beacon;
  private final HashMap<String, CacheItem> cache;

  private IClosable readAndParse(final String id, final IParser parser)
    throws IOException, ParserException {
    final InputStream is = beacon.getReader(id);

    if (is != null) {
      try {
        return parser.parse(is);
      } catch (final ParserException e) {
        throw new ParserException("Error parsing: " + id, e);
      } finally {
        is.close();
      }
    }

    return null;
  }

  public Cache(final File rootDir) throws FileNotFoundException {
    beacon = new FileBeacon(rootDir);
    cache = new HashMap<String, CacheItem>();
  }

  public void close() throws CacheException {
    for (final CacheItem cacheItem : cache.values()) {
      cacheItem.close();
    }

    cache.clear();
  }

  public void deleteFromCache(final String id) throws CacheException {
    cache.get(id).close();
    cache.remove(id);
  }

  public void addToCache(final String id, final IClosable data) {
    cache.put(id, new CacheItem(id, data));
  }

  public void readToCache(final String id, final IParser parser)
    throws IOException, ParserException {
    if (!cache.containsKey(id)) {
      cache.put(id, new CacheItem(id, readAndParse(id, parser)));
    }
  }

  // TODO: Better naming for getCold and getWarm
  public IClosable getCold(final String id, final IParser parser)
    throws IOException, ParserException {
    final IClosable data;
    if (cache.containsKey(id)) {
      data = cache.get(id).getData();
    } else {
      data = readAndParse(id, parser);
      cache.put(id, new CacheItem(id, data));
    }

    return data;
  }

  public IClosable getWarm(final String id) throws ObjectNotInCacheException {
    final CacheItem c = cache.get(id);
    if (c != null) {
      return c.getData();
    }

    throw new ObjectNotInCacheException(id);
  }

  public int itemsInCache() {
    return cache.size();
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    for (final CacheItem c : cache.values()) {
      sb.append(c);
    }

    return sb.toString();
  }
}
