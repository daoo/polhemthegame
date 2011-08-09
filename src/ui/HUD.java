/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import entities.Player;

// TODO: Fix HUD
public class HUD {
  public static final int height = 75;
  public static final int width  = 1024;
  private final Image     img_hud;
  private final Graphics  gfx_hud;
  private final int       x, y;
  private int             money, kills;

  public HUD(final int y, final Player p) throws SlickException {
    x = 0;
    this.y = y;

    // Set to -1 to force first draw
    money = -1;
    kills = -1;

    img_hud = new Image(HUD.width, HUD.height);
    gfx_hud = img_hud.getGraphics();
    gfx_hud.setBackground(Color.black);

    drawHUD(p);
  }

  private void drawHUD(final Player p) {
    gfx_hud.clear();

    money = p.getInventory().getMoney();
    kills = p.getStats().getKills();

    // TODO: Fix magic numbers
    gfx_hud.drawString("Money:", 850, y + 10);
    gfx_hud.drawString("Kills:", 850, y + 30);
    gfx_hud.drawString(String.valueOf(money), 950, y + 10);
    gfx_hud.drawString(String.valueOf(kills), 950, y + 30);

    gfx_hud.flush();
  }

  public void draw() {
    img_hud.draw(x, y);
  }

  public void update(final Player p) {
    drawHUD(p);
  }
}
