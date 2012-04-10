package main;

import java.awt.Font;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class FontHelper {
  private static final ColorEffect COLOR_WHITE = new ColorEffect(java.awt.Color.white);

  public static UnicodeFont getFont(String name, int size)
      throws SlickException {
    Font font = new Font(name, Font.PLAIN, size);
    UnicodeFont ufont = new UnicodeFont(font);

    ufont.getEffects().add(COLOR_WHITE);

    ufont.addAsciiGlyphs();
    ufont.loadGlyphs();

    return ufont;
  }

  public static Image renderString(String s, UnicodeFont font)
      throws SlickException {
    int width = font.getWidth(s);
    int height = font.getHeight(s);

    Image img = new Image(width, height);
    Graphics g = img.getGraphics();
    g.setFont(font);
    g.clear();
    g.setAntiAlias(false);
    g.drawString(s, 0, 0);
    g.flush();

    return img;
  }
}
