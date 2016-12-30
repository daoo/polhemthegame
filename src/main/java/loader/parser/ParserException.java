/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package loader.parser;

public class ParserException extends Exception {
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
