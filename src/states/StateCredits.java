/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package states;

import game.CacheTool;

import java.io.IOException;
import java.util.ArrayList;

import loader.parser.ParserException;
import main.FontHelper;
import main.Locator;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;

import states.credits.Credits;
import states.credits.ImageWithLocation;
import util.Node;

public class StateCredits implements IState {
  private static final int FONT_SIZE_SMALL = 24;
  private static final int FONT_SIZE_BIG = 30;

  private static final int DEFAULT_Y_SPEED = 50;
  private static final int Y_SPEED_DELTA = 50;
  private static final int DEFAULT_SPACING = 5;
  private static final int EMPTY_SPACING = 30;

  private float speed;
  private float pos_y;

  private final ArrayList<ImageWithLocation> credits;

  private final UnicodeFont font_large, font_small;

  public StateCredits(int windowWidth, int windowHeight) throws SlickException,
      ParserException, IOException {
    font_large = FontHelper.getFont("Verdana", FONT_SIZE_BIG);
    font_small = FontHelper.getFont("Verdana", FONT_SIZE_SMALL);

    speed = DEFAULT_Y_SPEED;
    pos_y = windowHeight;

    float tmp_x = windowWidth / 2.0f;
    float tmp_y = 0;

    credits = new ArrayList<>();
    for (String s : Credits.CreditsText) {
      if (!s.isEmpty()) {
        Image tmp;
        if (s.startsWith("img:")) {
          tmp = CacheTool.getImage(Locator.getCache(), s.substring(4));
        } else if (s.startsWith("big:")) {
          tmp = FontHelper.renderString(s.substring(4), font_large);
        } else {
          tmp = FontHelper.renderString(s, font_small);
        }

        float x = tmp_x - tmp.getWidth() / 2.0f;
        float y = tmp_y - tmp.getHeight() / 2.0f;

        credits.add(new ImageWithLocation(x, y, tmp));
        tmp_y += tmp.getHeight() + DEFAULT_SPACING;
      } else {
        tmp_y += EMPTY_SPACING;
      }
    }
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

    for (ImageWithLocation l : credits) {
      l.render(g);
    }
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
