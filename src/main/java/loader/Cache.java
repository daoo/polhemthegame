/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package loader;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import loader.parser.IParser;
import loader.parser.ParserException;

public class Cache implements Closeable {
  private final Map<String, Closeable> mCache = new HashMap<>();

  @Override
  public void close() throws IOException {
    for (Closeable item : mCache.values()) {
      item.close();
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
  public <T extends Closeable> T get(String id, IParser<T> parser) throws IOException, ParserException {
    assert id != null;
    assert parser != null;

    T data = (T) mCache.get(id);
    if (data == null) {
      data = readAndParse(id, parser);
      mCache.put(id, data);
    }

    return data;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (Closeable item : mCache.values()) {
      builder.append(item);
    }

    return builder.toString();
  }

  private <T extends Closeable> T readAndParse(String id, IParser<T> parser) throws IOException, ParserException {
    assert id != null;
    assert parser != null;

    final T result;
    // FIXME: Prepending the slash here is a hack and should be propagated.
    try (InputStream stream = getClass().getResourceAsStream('/' + id)) {
      result = parser.parse(stream);
    } catch (ParserException exception) {
      throw new ParserException("Error parsing: " + id, exception);
    }

    return result;
  }
}
