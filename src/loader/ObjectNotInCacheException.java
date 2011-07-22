/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package loader;

public class ObjectNotInCacheException extends CacheException {
  private static final long serialVersionUID = 1L;

  public ObjectNotInCacheException(final String message) {
    super(message);
  }
}
