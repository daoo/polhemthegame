/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package loader.parser;

import java.io.Closeable;
import java.io.InputStream;

public interface IParser<T extends Closeable> {
  /**
   * Parse a input stream.
   *
   * @param br the input stream to parse
   * @return new data
   * @throws ParserException if for some reason parsing fails
   */
  T parse(InputStream br) throws ParserException;
}
