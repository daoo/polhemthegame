/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.modes;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Graphics;

public class DebuggerMode implements IMode {
  private final GameMode game;
  private final float framelength;
  private boolean F5_down;

  public DebuggerMode(GameMode game) {
    this.game = game;
    framelength = 0.1f;

    F5_down = false;
  }

  @Override
  public void update(float dt) {
    if (Keyboard.isKeyDown(Keyboard.KEY_F5)) {
      if (!F5_down) {
        game.update(framelength);
      }

      F5_down = true;
    } else {
      F5_down = false;
    }
  }

  @Override
  public void render(Graphics g) {
    game.render(g);
  }
}