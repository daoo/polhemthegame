/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics;

public class Tile {
  public static final Tile ZERO = new Tile(0, 0);

  public final int         x, y;

  public Tile(final int x, final int y) {
    this.x = x;
    this.y = y;
  }

  public boolean sameAs(final Tile other) {
    return (x == other.x) && (y == other.y);
  }
}