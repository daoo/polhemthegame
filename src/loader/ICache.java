/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package loader;

import java.io.IOException;

import loader.data.IClosable;
import loader.parser.IParser;
import loader.parser.ParserException;

public interface ICache {
  void close() throws CacheException;

  void delete(String id) throws CacheException;

  IClosable getCold(String id, IParser parser)
    throws IOException, ParserException;

  IClosable getWarm(String id) throws ObjectNotInCacheException;

  int count();

}
