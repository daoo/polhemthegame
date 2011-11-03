/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package loader.data;

import loader.CacheException;

public class DataException extends CacheException {
  private static final long serialVersionUID = 1L;

  public DataException(Throwable cause) {
    super(cause);
  }

  public DataException(String message) {
    super(message);
  }
}
