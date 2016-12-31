/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics.animations;

@SuppressWarnings("InstanceVariableNamingConvention")
public class Tile {
  public static final Tile ZERO = new Tile(0, 0);

  public final int x;
  public final int y;

  public Tile(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public boolean isEqual(Tile other) {
    return x == other.x && y == other.y;
  }
}
