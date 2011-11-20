/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package loader.parser;

import java.io.Closeable;
import java.io.InputStream;

public interface IParser {
  /**
   * Parse a input stream.
   * @param br the input stream to parse
   * @throws ParserException if for some reason parsing fails
   * @return a new closable
   */
  Closeable parse(InputStream br) throws ParserException;
}
