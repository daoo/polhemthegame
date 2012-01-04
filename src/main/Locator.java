/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package main;

import loader.ICache;
import math.IRandom;
import ui.IUI;

/**
 * Service locator.
 * Implementation of the service locator pattern.
 */
public class Locator {
  private static ICache _cache;
  private static IRandom _random;
  private static IUI _ui;

  static {
    _cache  = null;
    _random = null;
    _ui     = null;
  }

  /**
   * Register a cache.
   * @param cache the cache, can not be null
   */
  public static void registerCache(ICache cache) {
    assert cache != null;

    _cache = cache;
  }

  /**
   * Register a random number generator.
   * @param random the RNG, can not be null
   */
  public static void registerRandom(IRandom random) {
    assert random != null;

    _random = random;
  }

  /**
   * Register an UI.
   * @param ui the UI, can not be null
   */
  public static void registerUI(IUI ui) {
    assert ui != null;

    _ui = ui;
  }

  /**
   * Retrive the cache.
   * Note that this can return null if no cache have been registered.
   * @return the registered cache
   */
  public static ICache getCache() {
    return _cache;
  }

  /**
   * Retrive the RNG.
   * Note that this can return null if no RNG have been registered.
   * @return the registered RNG
   */
  public static IRandom getRandom() {
    return _random;
  }

  /**
   * Retrive the UI.
   * Note this can return null if no UI have been registered.
   * @return the registered UI
   */
  public static IUI getUI() {
    return _ui;
  }
}
