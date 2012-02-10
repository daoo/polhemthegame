/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package loader.data.json;

public class ConfigData {
  public PlayerBinds player1, player2;

  public static class PlayerBinds {
    public int walkUp, walkDown, walkLeft, walkRight;
    public int fire, nextWeapon, buy;
  }
}
