/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.credits;

import game.CacheTool;

import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;

import loader.parser.ParserException;
import main.App;
import main.Locator;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;



public class StateCredits extends BasicGameState {
  private static final int FONT_SIZE_SMALL = 24;
  private static final int FONT_SIZE_BIG = 30;

  private static final int DEFAULT_Y_SPEED = 100;
  private static final int DEFAULT_SPACING = 5;
  private static final int EMPTY_SPACING = 30;

  private final int state_id;

  private float speed;
  private float pos_y;

  private final ArrayList<ImageWithLocation> credits;

  private boolean exit, menu;

  private UnicodeFont font_large, font_small;

  public StateCredits(final int stateId) {
    super();

    credits = new ArrayList<ImageWithLocation>();

    state_id = stateId;
  }

  @Override
  public void enter(final GameContainer gc, final StateBasedGame sb)
    throws SlickException {
    gc.setClearEachFrame(true);
    gc.setTargetFrameRate(App.MAX_FPS);

    speed = DEFAULT_Y_SPEED;
    pos_y = gc.getHeight();

    float tmp_x = App.WIDTH / 2.0f;
    float tmp_y = 0;

    credits.clear();
    credits.ensureCapacity(Credits.CreditsText.length);
    for (final String s : Credits.CreditsText) {
      if (!s.isEmpty()) {
        try {
          ImageWithLocation img;

          if (s.startsWith("img:")) {
            Image a = CacheTool.getImage(Locator.getCache(), s.substring(4));
            float x = tmp_x - a.getWidth() / 2.0f;
            float y = tmp_y - a.getHeight() / 2.0f;
            img = new ImageWithLocation(x, y, a);
          } else if (s.startsWith("big:")) {
            img = line_from_string(tmp_x, tmp_y, s.substring(4), font_large);
          } else {
            img = line_from_string(tmp_x, tmp_y, s, font_small);
          }

          credits.add(img);
          tmp_y += img.getHeight() + DEFAULT_SPACING;
        } catch (final IOException ex) {
          ex.printStackTrace();
        } catch (final ParserException ex) {
          ex.printStackTrace();
        } catch (final SlickException ex) {
          ex.printStackTrace();
        }
      } else {
        tmp_y += EMPTY_SPACING;
      }
    }
  }

  @Override
  public int getID() {
    return state_id;
  }

  @Override
  public void init(final GameContainer gc, final StateBasedGame sb)
    throws SlickException {
    gc.setVerbose(false);

    font_large = get_font("Verdana", FONT_SIZE_BIG);
    font_small = get_font("Verdana", FONT_SIZE_SMALL);
  }

  @Override
  public void keyPressed(final int key, final char c) {
    if (key == Input.KEY_ESCAPE) {
      menu = true;
    }
    if (key == Input.KEY_F2) {
      exit = true;
    } else if (key == Input.KEY_SPACE) {
      speed += 50;
    }
  }

  @Override
  public void render(final GameContainer gc, final StateBasedGame sb,
                     final Graphics g) throws SlickException {
    g.pushTransform();
    g.translate(0, pos_y);

    for (final ImageWithLocation l : credits) {
      l.render(g);
    }
  }

  @Override
  public void update(final GameContainer gc, final StateBasedGame sb,
                     final int delta) throws SlickException {
    if (exit) {
      gc.exit();
    }

    if (menu) {
      sb.enterState(App.MAINMENU);
    }

    final float dt = delta / 1000.0f;

    pos_y -= speed * dt;
  }

  private UnicodeFont get_font(final String name, final int size)
    throws SlickException {
    final Font font = new Font(name, Font.PLAIN, size);
    final UnicodeFont ufont = new UnicodeFont(font);
    ufont.getEffects().add(new ColorEffect(java.awt.Color.white));
    ufont.addAsciiGlyphs();
    ufont.loadGlyphs();

    return ufont;
  }

  private ImageWithLocation line_from_string(final float x, final float y,
    final String s, final UnicodeFont font)
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
