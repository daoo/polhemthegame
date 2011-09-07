/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package main;

import loader.ICache;
import math.IRandom;

public class Locator {
  private static ICache _cache;
  private static IRandom _random;

  public static void registerCache(final ICache cache) {
    assert (cache != null);

    _cache = cache;
  }

  public static void registerRandom(final IRandom random) {
    assert (random != null);

    _random = random;
  }

  public static ICache getCache() {
    return _cache;
  }

  public static IRandom getRandom() {
    return _random;
  }
}
