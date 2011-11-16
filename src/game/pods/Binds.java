package game.pods;

import org.lwjgl.input.Keyboard;

public class Binds {
  public final int walkUp, walkDown, walkLeft, walkRight;

  public final int fire, nextWeapon;

  public Binds() {
    walkUp = Keyboard.KEY_W;
    walkDown = Keyboard.KEY_S;
    walkLeft = Keyboard.KEY_A;
    walkRight = Keyboard.KEY_D;

    fire = Keyboard.KEY_SPACE;
    nextWeapon = Keyboard.KEY_TAB;
  }
}
