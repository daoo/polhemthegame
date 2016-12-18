/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.misc;

import game.config.Config;
import game.ui.IUI;
import loader.Cache;
import util.Random;

/**
 * Service locator.
 * Implementation of the service locator pattern.
 */
public class Locator {
  private static Cache _cache;
  private static Random _random;
  private static IUI _ui;
  private static Config _config;

  static {
    _cache  = null;
    _random = null;
    _ui     = null;
    _config = null;
  }

  /**
   * Clear all registered services.
   */
  public static void close() {
    _cache  = null;
    _random = null;
    _ui     = null;
    _config = null;
  }

  /**
   * Register a cache.
   * @param cache the cache, can not be null
   */
  public static void registerCache(Cache cache) {
    assert cache != null;

    _cache = cache;
  }

  /**
   * Register a random number generator.
   * @param random the RNG, can not be null
   */
  public static void registerRandom(Random random) {
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
   * Register a new config.
   * @param config the config, can not be null
   */
  public static void registerConfig(Config config) {
    assert config != null;

    _config = config;
  }

  /**
   * Retrieve the cache.
   * Note that this can return null if no cache have been registered.
   * @return the registered cache, or null
   */
  public static Cache getCache() {
    return _cache;
  }

  /**
   * Retrieve the RNG.
   * Note that this can return null if no RNG have been registered.
   * @return the registered RNG, or null
   */
  public static Random getRandom() {
    return _random;
  }

  /**
   * Retrieve the UI.
   * Note this can return null if no UI have been registered.
   * @return the registered UI, or null
   */
  public static IUI getUI() {
    return _ui;
  }

  /**
   * Retrieve the config.
   * Note that this can return null if no config has been registered.
   * @return the registered config, or null
   */
  public static Config getConfig() {
    return _config;
  }
}
