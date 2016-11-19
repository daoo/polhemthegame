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
  private final HashMap<String, IData> cache;

  public Cache() {
    cache = new HashMap<>();
  }

  @Override
  public void close() throws IOException {
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

    final IData result;
    // FIXME: Prepending the slash here is a hack and should be propagated.
    try (InputStream is = getClass().getResourceAsStream('/' + id)) {
      result = parser.parse(is);
    } catch (ParserException ex) {
      throw new ParserException("Error parsing: " + id, ex);
    }

    return result;
  }
}
