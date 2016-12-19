package util;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Map;

public class FontHelper {
  private static final String FORMATER_SEPARATOR = ":";
  private static final String DEFAULT_FORMATER = "default";

  private static final ColorEffect COLOR_WHITE = new ColorEffect(Color.white);

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

  /**
   * Draw a set of lines to a new image.
   * @param formaters line formating rules
   * @param spacing vertical spacing between the lines
   * @param lines the lines to render
   * @return a new image
   * @throws SlickException see {@link Image} and {@link Graphics}
   */
  public static Image renderString(Map<String, UnicodeFont> formaters,
      int spacing, String[] lines) throws SlickException {
    ArrayList<Pair<UnicodeFont, String>> list = new ArrayList<>(lines.length);

    int w = 0;
    int h = 0;
    for (String line : lines) {
      int i = line.indexOf(FORMATER_SEPARATOR);
      String formater;
      String text;
      if (i == -1) {
        formater = DEFAULT_FORMATER;
        text = line;
      } else {
        formater = line.substring(0, i);
        text = line.substring(i + 1);
      }

      UnicodeFont font = formaters.get(formater);
      int tw = font.getWidth(text);
      int th = font.getHeight(text);
      w += tw;
      h += th + spacing;

      list.add(new Pair<>(font, text));
    }

    Image img = new Image(w, h);
    Graphics g = img.getGraphics();
    g.clear();
    g.setAntiAlias(false);

    int middle = w / 2;
    for (Pair<UnicodeFont, String> line : list) {
      g.setFont(line.fst);
      g.drawString(line.snd, middle - line.fst.getWidth(line.snd) / 2, 0);

      g.translate(0, spacing + line.fst.getHeight(line.snd));
    }
    g.flush();

    return img;
  }
}
