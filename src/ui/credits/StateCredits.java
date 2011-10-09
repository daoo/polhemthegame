/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package ui.credits;

import game.CacheTool;

import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;

import loader.parser.ParserException;
import main.GameStateManager;
import main.IGameState;
import main.Launcher;
import main.Locator;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class StateCredits implements IGameState {
  private static final int FONT_SIZE_SMALL = 24;
  private static final int FONT_SIZE_BIG = 30;

  private static final int DEFAULT_Y_SPEED = 100;
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

    final float tmp_x = Launcher.WIDTH / 2.0f;
    float tmp_y = 0;

    credits = new ArrayList<ImageWithLocation>(Credits.CreditsText.length);
    for (final String s : Credits.CreditsText) {
      if (!s.isEmpty()) {
        ImageWithLocation img;

        if (s.startsWith("img:")) {
          final Image a = CacheTool.getImage(Locator.getCache(), s.substring(4));
          final float x = tmp_x - a.getWidth() / 2.0f;
          final float y = tmp_y - a.getHeight() / 2.0f;
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
  public void render(final Graphics g) throws SlickException {
    g.pushTransform();
    g.translate(0, pos_y);

    for (final ImageWithLocation l : credits) {
      l.render(g);
    }
  }

  @Override
  public void update(final GameStateManager stateGame, final float delta) {
    if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
      stateGame.enterMainMenu();
    } else if (Keyboard.isKeyDown(Keyboard.KEY_F2)) {
      stateGame.quit();
    } else if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
      speed += 50;
    }

    pos_y -= speed * delta;
  }

  private UnicodeFont getFont(final String name, final int size)
    throws SlickException {
    final Font font = new Font(name, Font.PLAIN, size);
    final UnicodeFont ufont = new UnicodeFont(font);

    final ArrayList<ColorEffect> tmp = (ArrayList<ColorEffect>) ufont.getEffects();
    tmp.add(COLOR_WHITE);

    ufont.addAsciiGlyphs();
    ufont.loadGlyphs();

    return ufont;
  }

  private ImageWithLocation lineFromString(final float x, final float y,
                                             final String s,
                                             final UnicodeFont font)
    throws SlickException {
    final int width = font.getWidth(s);
    final int height = font.getHeight(s);

    final Image img = new Image(width, height);
    final Graphics g = img.getGraphics();
    g.setFont(font);
    g.clear();
    g.setAntiAlias(false);
    g.drawString(s, 0, 0);
    g.flush();

    return new ImageWithLocation(x - (width / 2.0f), y - (height / 2.0f), img);
  }
}
