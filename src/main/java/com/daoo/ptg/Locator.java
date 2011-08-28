package main;

import loader.ICache;

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
