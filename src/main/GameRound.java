/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package main;

import game.modes.Debugger;
import game.modes.Game;
import game.modes.IMode;

import java.io.IOException;

import loader.data.DataException;
import loader.data.json.CampaignData;
import loader.parser.GsonParser;
import loader.parser.ParserException;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import ui.hud.HUD;

public class GameRound implements IGameState {
  private static final boolean DEBUG = false;

  private final IMode mode;

  public GameRound() throws ParserException, IOException, DataException {
    CampaignData campaign = (CampaignData)
      Locator.getCache().getCold(
        "campaigns/polhem.js",
        new GsonParser(CampaignData.class));

    if (DEBUG) {
      mode = new Debugger(new Game(campaign, 0, HUD.HEIGHT,
        Launcher.WIDTH, Launcher.HEIGHT - (2 * HUD.HEIGHT)));
    } else {
      mode = new Game(campaign, 0, HUD.HEIGHT,
        Launcher.WIDTH, Launcher.HEIGHT - (2 * HUD.HEIGHT));
    }
  }

  @Override
  public void render(Graphics g) throws SlickException {
    mode.render(g);
  }

  @Override
  public void update(GameStateManager stateGame, float delta) {
    if (Keyboard.isKeyDown(Keyboard.KEY_F2)) {
      stateGame.quit();
    } else {
      mode.update(delta);
    }
  }
}
