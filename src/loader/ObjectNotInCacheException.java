/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package loader;

public class ObjectNotInCacheException extends CacheException {
  private static final long serialVersionUID = 1L;

  public ObjectNotInCacheException(String message) {
    super(message);
  }
}
