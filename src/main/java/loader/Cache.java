/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package loader;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import loader.parser.IParser;
import loader.parser.ParserException;

public class Cache implements Closeable {
  private final HashMap<String, IData> mCache;

  public Cache() {
    mCache = new HashMap<>();
  }

  @Override
  public void close() throws IOException {
    for (IData cacheItem : mCache.values()) {
      cacheItem.close();
    }

    mCache.clear();
  }

  /**
   * Retrieve an item from the cache or disk.
   * If there is no item in the cache, the parser will be used to read if from
   * the disk.
   *
   * @param id the id of the item to retrieve
   * @param parser the parser to use if a disk access is needed
   * @return the requested data
   * @throws IOException if the data is not found on the disk
   * @throws ParserException if parsing the data fails
   */
  public IData get(String id, IParser parser) throws IOException, ParserException {
    assert id != null;
    assert parser != null;

    IData data = mCache.get(id);
    if (data == null) {
      data = readAndParse(id, parser);
      mCache.put(id, data);
    }

    return data;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (IData c : mCache.values()) {
      sb.append(c);
    }

    return sb.toString();
  }

  private IData readAndParse(String id, IParser parser) throws IOException, ParserException {
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
