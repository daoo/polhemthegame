/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package loader.data.json;

public class ConfigData {
  public PlayerBinds player1, player2;

  public static class PlayerBinds {
    public String walkUp, walkDown, walkLeft, walkRight;
    public String fire, nextWeapon, previousWeapon, buy;
    public String weapon0, weapon1, weapon2, weapon3, weapon4;
  }
}
