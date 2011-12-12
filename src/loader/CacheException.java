/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package loader;

public class CacheException extends Exception {
  private static final long serialVersionUID = 1L;

  public CacheException(Throwable cause) {
    super(cause);
  }

  public CacheException(String message) {
    super(message);
  }

  public CacheException(String message, Throwable cause) {
    super(message, cause);
  }
}
