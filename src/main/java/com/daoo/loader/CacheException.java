/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package com.daoo.loader;

public class CacheException extends Exception {
  private static final long serialVersionUID = 1L;

  private final Throwable   cause;

  public CacheException(final Throwable cause) {
    super();

    this.cause = cause;
  }

  public CacheException(final String message) {
    super(message);

    cause = null;
  }

  public CacheException(final String message, final Throwable cause) {
    super(message);
    this.cause = cause;
  }

  @Override
  public Throwable getCause() {
    return cause;
  }
}
