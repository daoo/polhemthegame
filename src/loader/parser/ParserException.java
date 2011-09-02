/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package com.daoo.loader.parser;

import com.daoo.loader.CacheException;

public class ParserException extends CacheException {
  private static final long serialVersionUID = 1L;

  public ParserException(final Throwable cause) {
    super(cause);
  }

  public ParserException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
