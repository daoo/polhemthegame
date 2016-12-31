/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.config;

import org.lwjgl.input.Keyboard;

/**
 * POJO for keeping track of player bindings.
 */
@SuppressWarnings("InstanceVariableNamingConvention")
public class Binds {
  public static final Binds DEFAULT = new Binds();

  public final int walkUp;
  public final int walkDown;
  public final int walkLeft;
  public final int walkRight;

  public final int fire;
  public final int previousWeapon;
  public final int nextWeapon;
  public final int buy;
  public final int weapon0;
  public final int weapon1;
  public final int weapon2;
  public final int weapon3;
  public final int weapon4;

  /**
   * Use default bindings.
   */
  private Binds() {
    walkUp = Keyboard.KEY_W;
    walkDown = Keyboard.KEY_S;
    walkLeft = Keyboard.KEY_A;
    walkRight = Keyboard.KEY_D;
    fire = Keyboard.KEY_SPACE;
    nextWeapon = Keyboard.KEY_E;
    previousWeapon = Keyboard.KEY_Q;
    buy = Keyboard.KEY_F;

    weapon0 = Keyboard.KEY_1;
    weapon1 = Keyboard.KEY_2;
    weapon2 = Keyboard.KEY_3;
    weapon3 = Keyboard.KEY_4;
    weapon4 = Keyboard.KEY_5;
  }

  /**
   * Use specific bindings.
   * Uses reflection to decode the key names into their integer values.
   *
   * @param walkUp a key
   * @param walkDown a key
   * @param walkLeft a key
   * @param walkRight a key
   * @param fire a key
   * @param nextWeapon a key
   * @param buy a key
   */
  public Binds(
      String walkUp, String walkDown, String walkLeft, String walkRight, String fire,
      String previousWeapon, String nextWeapon, String buy, String weapon0, String weapon1,
      String weapon2, String weapon3, String weapon4) {
    this.walkUp = getKeyOrThrow(walkUp);
    this.walkDown = getKeyOrThrow(walkDown);
    this.walkLeft = getKeyOrThrow(walkLeft);
    this.walkRight = getKeyOrThrow(walkRight);

    this.fire = getKeyOrThrow(fire);
    this.buy = getKeyOrThrow(buy);

    this.previousWeapon = getKeyOrThrow(previousWeapon);
    this.nextWeapon = getKeyOrThrow(nextWeapon);

    this.weapon0 = getKeyOrThrow(weapon0);
    this.weapon1 = getKeyOrThrow(weapon1);
    this.weapon2 = getKeyOrThrow(weapon2);
    this.weapon3 = getKeyOrThrow(weapon3);
    this.weapon4 = getKeyOrThrow(weapon4);
  }

  private static int getKeyOrThrow(String name) {
    int key = Keyboard.getKeyIndex("KEY_" + name);
    if (key == Keyboard.KEY_NONE) {
      throw new IllegalArgumentException("Key '" + name + "' is invalid.");
    }
    return key;
  }
}
