/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package loader;

import java.io.Closeable;
import java.io.IOException;

import loader.parser.IParser;
import loader.parser.ParserException;

public interface ICache extends Closeable {
  /**
   * Delete an item from the cache.
   * @param id the item to delete
   * @throws IOException if closing the item fails
   */
  void delete(String id) throws IOException;

  /**
   * Retrieve an item from the cache or disk.
   * If there is no item in the cache, the parser will be used to read if from
   * the disk.
   * @param id the id of the item to retrieve
   * @param parser the parser to use if a disk access is needed
   * @return the requested data
   * @throws IOException if the data is not found on the disk
   * @throws ParserException if parsing the data fails
   */
  IData getCold(String id, IParser parser) throws IOException, ParserException;

  /**
   * Retrieve an item from the cache with no potential disk access.
   * @param id the id of the item to retrieve
   * @return the requested data
   * @throws ObjectNotInCacheException if the data isn't in the cache
   */
  IData getWarm(String id) throws ObjectNotInCacheException;

  /**
   * Return the number of items in this cache.
   * @return the number of items in this cache
   */
  int count();
}
