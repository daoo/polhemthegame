/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package credits;

import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import loader.parser.ParserException;
import main.CacheTool;
import main.Launcher;
import math.Vector2;

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

  private final ArrayList<Line> credits;

  private boolean               exit;
  private UnicodeFont           font_large, font_small;
  private final int             state_id;

  public StateCredits(final int stateId) {
    super();

    credits = new ArrayList<Line>();

    state_id = stateId;
  }

  @Override
  public void enter(final GameContainer gc, final StateBasedGame sb)
    throws SlickException {
    gc.setClearEachFrame(true);
    gc.setTargetFrameRate(Launcher.MAX_FPS);

    final float speed = -DEFAULT_Y_SPEED;
    final float tmp_x = Launcher.WIDTH / 2.0f;
    float tmp_y = Launcher.HEIGHT;

    credits.clear();
    credits.ensureCapacity(Credits.CreditsText.length);
    for (final String s : Credits.CreditsText) {
      if (!s.isEmpty()) {
        try {
          Line l;
          if (s.startsWith("img:")) {
            l = new Line(tmp_x, tmp_y, speed,
              CacheTool.getImage(Launcher.cache, s.substring(4)));
          } else if (s.startsWith("big:")) {
            l = line_from_string(tmp_x, tmp_y, speed, s.substring(4), font_large);
          } else {
            l = line_from_string(tmp_x, tmp_y, speed, s, font_small);
          }

          credits.add(l);
          tmp_y += l.getHeight() + DEFAULT_SPACING;
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
    if (key == Input.KEY_F2) {
      exit = true;
    } else if (key == Input.KEY_ENTER || key == Input.KEY_SPACE) {
      for (final Line l : credits) {
        l.addVelocity(new Vector2(0, -50));
      }
    }
  }

  @Override
  public void render(final GameContainer gc, final StateBasedGame sb,
                     final Graphics g) throws SlickException {
    for (final Line l : credits) {
      l.draw(g);
    }
  }

  @Override
  public void update(final GameContainer gc, final StateBasedGame sb,
                     final int delta) throws SlickException {
    if (exit) {
      gc.exit();
    }

    if (credits.isEmpty()) {
      sb.enterState(Launcher.MAINMENU);
    }

    final float dt = delta / 1000.0f;

    final Iterator<Line> it = credits.iterator();
    while (it.hasNext()) {
      final Line l = it.next();
      l.update(dt);

      if ((l.getY() + l.getHeight()) <= 0) {
        it.remove();
      }
    }
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

  private Line line_from_string(final float x, final float y,
                                final float speed, final String s, final UnicodeFont font)
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

    return new Line(x - (width / 2.0f), y - (height / 2.0f), speed, img);
  }
}
