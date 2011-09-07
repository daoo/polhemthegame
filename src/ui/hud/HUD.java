/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package ui.hud;

import game.entities.Player;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

// TODO: Hud drawing

public class HUD {
  public static final int     HEIGHT  = 75;
  public static final int     WIDTH   = 1024;

  private static final int    PADDING = 5;

  private static final String MONEY   = "Money: ";
  private static final String KILLS   = "Kills: ";

  private final Image         img_hud;
  private final Graphics      gfx_hud;

  private final int           stats_x, stats_y;
  private final int           kills_y;

  private int                 money, kills;

  public HUD(final Player player) throws SlickException {
    money = -1;
    kills = -1;

    img_hud = new Image(WIDTH, HEIGHT);
    gfx_hud = img_hud.getGraphics();
    gfx_hud.setBackground(Color.black);

    final Font font = gfx_hud.getFont();
    final String maxInt = String.valueOf(Integer.MAX_VALUE);

    final int w = Math.max(font.getWidth(MONEY + maxInt),
                           font.getWidth(KILLS + maxInt));

    stats_x = WIDTH - w - PADDING;
    stats_y = PADDING;
    kills_y = font.getHeight(KILLS) + PADDING;

    drawHUD();
  }

  private void drawHUD() {
    final int m = -1; // TODO
    final int k = -1; // TODO
    if (m != money || k != kills) {
      gfx_hud.clear();

      money = m;
      kills = k;

      final String mStr = MONEY + String.valueOf(money);
      final String kStr = KILLS + String.valueOf(kills);

      gfx_hud.drawString(mStr, stats_x, stats_y);
      gfx_hud.drawString(kStr, stats_x, kills_y);

      gfx_hud.flush();
    }
  }

  public void render(final Graphics g) {
    g.drawImage(img_hud, 0, 0);
  }

  public void update() {
    drawHUD();
  }
}
