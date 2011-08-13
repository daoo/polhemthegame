/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package loader.data;

import loader.CacheException;

public class DataException extends CacheException {
  private static final long serialVersionUID = 1L;

  public DataException(final Throwable cause) {
    super(cause);
  }

  public DataException(final String message) {
    super(message);
  }
}
