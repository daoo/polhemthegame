/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package com.daoo.loader.parser;

import java.io.InputStream;

import com.daoo.loader.data.IClosable;

public interface IParser {
  IClosable parse(InputStream br) throws ParserException;
}
