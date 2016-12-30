/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.misc;

import game.config.Config;
import game.ui.hud.UI;
import loader.Cache;
import util.Random;

/**
 * Service locator.
 */
public final class Locator {
  private static Cache s_cache = null;
  private static Random s_random = null;
  private static UI s_ui = null;
  private static Config s_config = null;

  private Locator() {
  }

  /**
   * Register a cache.
   *
   * @param cache the cache, can not be null
   */
  public static void registerCache(Cache cache) {
    assert cache != null;

    s_cache = cache;
  }

  /**
   * Register a random number generator.
   *
   * @param random the RNG, can not be null
   */
  public static void registerRandom(Random random) {
    assert random != null;

    s_random = random;
  }

  /**
   * Register an UI.
   *
   * @param ui the UI, can not be null
   */
  public static void registerUI(UI ui) {
    assert ui != null;

    s_ui = ui;
  }

  /**
   * Register a new config.
   *
   * @param config the config, can not be null
   */
  public static void registerConfig(Config config) {
    assert config != null;

    s_config = config;
  }

  /**
   * Retrieve the cache.
   * Note that this can return null if no cache have been registered.
   *
   * @return the registered cache, or null
   */
  public static Cache getCache() {
    return s_cache;
  }

  /**
   * Retrieve the RNG.
   * Note that this can return null if no RNG have been registered.
   *
   * @return the registered RNG, or null
   */
  public static Random getRandom() {
    return s_random;
  }

  /**
   * Retrieve the UI.
   * Note this can return null if no UI have been registered.
   *
   * @return the registered UI, or null
   */
  public static UI getUI() {
    return s_ui;
  }

  /**
   * Retrieve the config.
   * Note that this can return null if no config has been registered.
   *
   * @return the registered config, or null
   */
  public static Config getConfig() {
    return s_config;
  }
}
