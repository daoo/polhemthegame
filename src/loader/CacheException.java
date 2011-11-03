/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package loader;

public class CacheException extends Exception {
  private static final long serialVersionUID = 1L;

  private Throwable cause;

  public CacheException(Throwable cause) {
    super();

    this.cause = cause;
  }

  public CacheException(String message) {
    super(message);

    cause = null;
  }

  public CacheException(String message, Throwable cause) {
    super(message);
    this.cause = cause;
  }

  @Override
  public Throwable getCause() {
    return cause;
  }
}
