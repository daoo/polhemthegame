/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package loader.parser;

import java.io.InputStream;

import loader.IData;

public interface IParser {
  /**
   * Parse a input stream.
   * @param br the input stream to parse
   * @throws ParserException if for some reason parsing fails
   * @return new data
   */
  IData parse(InputStream br) throws ParserException;
}
