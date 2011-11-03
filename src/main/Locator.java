/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package main;

import loader.ICache;
import math.IRandom;
import ui.IUI;

public class Locator {
  private static ICache _cache;
  private static IRandom _random;
  private static IUI _ui;

  public static void registerCache(ICache cache) {
    assert (cache != null);

    _cache = cache;
  }

  public static void registerRandom(IRandom random) {
    assert (random != null);

    _random = random;
  }

  public static void registerUI(IUI ui) {
    assert (ui != null);

    _ui = ui;
  }

  public static ICache getCache() {
    return _cache;
  }

  public static IRandom getRandom() {
    return _random;
  }

  public static IUI getUI() {
    return _ui;
  }
}
