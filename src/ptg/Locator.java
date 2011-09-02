/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package com.daoo.ptg;

import com.daoo.loader.ICache;

public class Locator {
  private static ICache _cache;
  
  public static void registerCache(final ICache cache) {
    assert (cache != null);
    
    _cache = cache;
  }
  
  public static ICache getCache() {
    return _cache;
  }
}
