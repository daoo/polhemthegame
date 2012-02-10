/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.types;

import org.lwjgl.input.Keyboard;

/**
 * Simple POD for keeping track of player bindings.
 */
public class Binds {
  public final int walkUp, walkDown, walkLeft, walkRight;

  public final int fire, nextWeapon, buy;

  /**
   * Use default bindings.
   */
  public Binds() {
    walkUp     = Keyboard.KEY_W;
    walkDown   = Keyboard.KEY_S;
    walkLeft   = Keyboard.KEY_A;
    walkRight  = Keyboard.KEY_D;
    fire       = Keyboard.KEY_SPACE;
    nextWeapon = Keyboard.KEY_TAB;
    buy        = Keyboard.KEY_1;
  }

  /**
   * Use specific bindings
   * @param walkUp a key
   * @param walkDown a key
   * @param walkLeft a key
   * @param walkRight a key
   * @param fire a key
   * @param nextWeapon a key
   * @param buy a key
   */
  public Binds(int walkUp, int walkDown, int walkLeft, int walkRight, int fire,
      int nextWeapon, int buy) {
    this.walkUp     = walkUp;
    this.walkDown   = walkDown;
    this.walkLeft   = walkLeft;
    this.walkRight  = walkRight;
    this.fire       = fire;
    this.nextWeapon = nextWeapon;
    this.buy        = buy;
  }
}
