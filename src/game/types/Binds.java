/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.types;

import org.lwjgl.input.Keyboard;

/**
 * Simple POD for keeping track of player bindings.
 */
public class Binds {
  private static final String KEY_PREFIX = "KEY_";

  public final int walkUp, walkDown, walkLeft, walkRight;

  public final int fire, previousWeapon, nextWeapon, buy;
  public final int weapon0, weapon1, weapon2, weapon3, weapon4;

  /**
   * Use default bindings.
   */
  public Binds() {
    walkUp         = Keyboard.KEY_W;
    walkDown       = Keyboard.KEY_S;
    walkLeft       = Keyboard.KEY_A;
    walkRight      = Keyboard.KEY_D;
    fire           = Keyboard.KEY_SPACE;
    nextWeapon     = Keyboard.KEY_E;
    previousWeapon = Keyboard.KEY_Q;
    buy            = Keyboard.KEY_F;

    weapon0 = Keyboard.KEY_1;
    weapon1 = Keyboard.KEY_2;
    weapon2 = Keyboard.KEY_3;
    weapon3 = Keyboard.KEY_4;
    weapon4 = Keyboard.KEY_5;
  }

  /**
   * Use specific bindings.
   * Uses reflection to decode the key names into their integer values.
   * @param walkUp a key
   * @param walkDown a key
   * @param walkLeft a key
   * @param walkRight a key
   * @param fire a key
   * @param nextWeapon a key
   * @param buy a key
   */
  public Binds(String walkUp, String walkDown, String walkLeft,
      String walkRight, String fire, String previousWeapon,
      String nextWeapon, String buy, String weapon0, String weapon1,
      String weapon2, String weapon3, String weapon4) {
    try {
      this.walkUp     = Keyboard.class.getField(KEY_PREFIX + walkUp).getInt(null);
      this.walkDown   = Keyboard.class.getField(KEY_PREFIX + walkDown).getInt(null);
      this.walkLeft   = Keyboard.class.getField(KEY_PREFIX + walkLeft).getInt(null);
      this.walkRight  = Keyboard.class.getField(KEY_PREFIX + walkRight).getInt(null);

      this.fire = Keyboard.class.getField(KEY_PREFIX + fire).getInt(null);
      this.buy  = Keyboard.class.getField(KEY_PREFIX + buy).getInt(null);

      this.previousWeapon = Keyboard.class.getField(KEY_PREFIX + previousWeapon).getInt(null);
      this.nextWeapon     = Keyboard.class.getField(KEY_PREFIX + nextWeapon).getInt(null);

      this.weapon0 = Keyboard.class.getField(KEY_PREFIX + weapon0).getInt(null);
      this.weapon1 = Keyboard.class.getField(KEY_PREFIX + weapon1).getInt(null);
      this.weapon2 = Keyboard.class.getField(KEY_PREFIX + weapon2).getInt(null);
      this.weapon3 = Keyboard.class.getField(KEY_PREFIX + weapon3).getInt(null);
      this.weapon4 = Keyboard.class.getField(KEY_PREFIX + weapon4).getInt(null);
    } catch (IllegalArgumentException | IllegalAccessException
        | NoSuchFieldException | SecurityException e) {
      throw new IllegalArgumentException("Reflection for key failed", e);
    }
  }
}
