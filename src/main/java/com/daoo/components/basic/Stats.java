/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package com.daoo.components.basic;

public class Stats {
  private int kills;

  public Stats() {
    kills = 0;
  }

  public void addKill() {
    ++kills;
  }

  public int getKills() {
    return kills;
  }
}
