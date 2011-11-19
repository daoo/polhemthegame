/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package loader;

import java.io.Closeable;
import java.io.IOException;

import loader.parser.IParser;
import loader.parser.ParserException;

public interface ICache extends Closeable {
  void delete(String id) throws IOException;

  Closeable getCold(String id, IParser parser)
    throws IOException, ParserException;

  Closeable getWarm(String id) throws ObjectNotInCacheException;

  int count();

}
