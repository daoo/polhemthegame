/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package loader.parser;

import java.io.InputStream;

import loader.data.IClosable;

public interface IParser {
  IClosable parse(InputStream br) throws ParserException;
}
