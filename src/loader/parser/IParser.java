/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package loader.parser;

import java.io.InputStream;

import loader.data.IClosable;

public interface IParser {
  IClosable parse(InputStream br) throws ParserException;
}
