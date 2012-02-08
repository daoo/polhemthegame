/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package states;

import game.CacheTool;

import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;

import loader.parser.ParserException;
import main.Launcher;
import main.Locator;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

import states.credits.Credits;
import states.credits.ImageWithLocation;
import util.Node;

public class StateCredits implements IState {
  private static final int FONT_SIZE_SMALL = 24;
  private static final int FONT_SIZE_BIG = 30;

  private static final int DEFAULT_Y_SPEED = 100;
  private static final int Y_SPEED_DELTA = 50;
  private static final int DEFAULT_SPACING = 5;
  private static final int EMPTY_SPACING = 30;

  private static final ColorEffect COLOR_WHITE = new ColorEffect(java.awt.Color.white);

  private float speed;
  private float pos_y;

  private final ArrayList<ImageWithLocation> credits;

  private final UnicodeFont font_large, font_small;

  public StateCredits() throws SlickException, ParserException, IOException {
    font_large = getFont("Verdana", FONT_SIZE_BIG);
    font_small = getFont("Verdana", FONT_SIZE_SMALL);

    speed = DEFAULT_Y_SPEED;
    pos_y = Launcher.HEIGHT;

    float tmp_x = Launcher.WIDTH / 2.0f;
    float tmp_y = 0;

    credits = new ArrayList<>();
    for (String s : Credits.CreditsText) {
      if (!s.isEmpty()) {
        ImageWithLocation img;

        if (s.startsWith("img:")) {
          Image a = CacheTool.getImage(Locator.getCache(), s.substring(4));
          float x = tmp_x - a.getWidth() / 2.0f;
          float y = tmp_y - a.getHeight() / 2.0f;
          img = new ImageWithLocation(x, y, a);
        } else if (s.startsWith("big:")) {
          img = lineFromString(tmp_x, tmp_y, s.substring(4), font_large);
        } else {
          img = lineFromString(tmp_x, tmp_y, s, font_small);
        }

        credits.add(img);
        tmp_y += img.getHeight() + DEFAULT_SPACING;
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

  @SuppressWarnings("unchecked")
  private static UnicodeFont getFont(String name, int size)
    throws SlickException {
    Font font = new Font(name, Font.PLAIN, size);
    UnicodeFont ufont = new UnicodeFont(font);

    ufont.getEffects().add(COLOR_WHITE);

    ufont.addAsciiGlyphs();
    ufont.loadGlyphs();

    return ufont;
  }

  private static ImageWithLocation lineFromString(float x, float y, String s,
      UnicodeFont font) throws SlickException {
    int width = font.getWidth(s);
    int height = font.getHeight(s);

    Image img = new Image(width, height);
    Graphics g = img.getGraphics();
    g.setFont(font);
    g.clear();
    g.setAntiAlias(false);
    g.drawString(s, 0, 0);
    g.flush();

    return new ImageWithLocation(x - (width / 2.0f), y - (height / 2.0f), img);
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
