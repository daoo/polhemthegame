/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package loader.parser;

import loader.CacheException;

public class ParserException extends CacheException {
  private static final long serialVersionUID = 1L;

  public ParserException(String message) {
    super(message);
  }

  public ParserException(Throwable cause) {
    super(cause);
  }

  public ParserException(String message, Throwable cause) {
    super(message, cause);
  }
}
