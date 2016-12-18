/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.states;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;

import java.util.HashMap;

import game.states.credits.Credits;
import util.FontHelper;
import util.Node;

public class StateCredits implements IState {
  private static final int FONT_SIZE_SMALL = 24;
  private static final int FONT_SIZE_BIG = 30;

  private static final int DEFAULT_Y_SPEED = 50;
  private static final int Y_SPEED_DELTA = 1;
  private static final int DEFAULT_SPACING = 10;

  private float speed;
  private float pos_y;

  private final int x;
  private final Image imgCredits;

  public StateCredits(int windowWidth, int windowHeight) throws SlickException {
    speed = DEFAULT_Y_SPEED;
    pos_y = windowHeight;

    HashMap<String, UnicodeFont> fonts = new HashMap<>();
    fonts.put("big", FontHelper.getFont("Verdana", FONT_SIZE_BIG));
    fonts.put("default", FontHelper.getFont("Verdana", FONT_SIZE_SMALL));
    imgCredits = FontHelper.renderString(fonts, DEFAULT_SPACING, Credits.CREDITS_TEXT);

    x = windowWidth / 2 - imgCredits.getWidth() / 2;
  }

  @Override
  public void start(StateManager stateManager) {
    // Do nothing
  }

  @Override
  public void end(StateManager stateManager) {
    // Do nothing
  }

  @Override
  public void render(Graphics g) throws SlickException {
    g.pushTransform();
    g.translate(0, pos_y);
    g.drawImage(imgCredits, x, 0);
    g.popTransform();
  }

  @Override
  public void update(StateManager stateGame, int delta) {
    if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
      stateGame.enterMainMenu();
    } else if (Keyboard.isKeyDown(Keyboard.KEY_F2)) {
      stateGame.quit();
    } else if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
      speed += Y_SPEED_DELTA;
    }

    pos_y -= speed * (delta / 1000.0f);
  }

  @Override
  public String debugString() {
    return "StateCredits";
  }

  @Override
  public Node<String> debugTree() {
    return new Node<>(debugString());
  }
}
