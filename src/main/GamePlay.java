/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package main;

import game.modes.DebuggerMode;
import game.modes.GameMode;
import game.modes.IMode;

import java.io.IOException;

import loader.data.DataException;
import loader.data.json.CampaignData;
import loader.parser.GsonParser;
import loader.parser.ParserException;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class GamePlay implements IGameState {
  private static final boolean DEBUG = false;

  private final IMode mode;

  public GamePlay() throws ParserException, IOException, DataException {
    CampaignData campaign = (CampaignData)
      Locator.getCache().getCold(
        "campaigns/polhem.js",
        new GsonParser(CampaignData.class));

    GameMode game = new GameMode(campaign, Launcher.WIDTH, Launcher.HEIGHT);
    if (DEBUG) {
      mode = new DebuggerMode(game);
    } else {
      mode = game;
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
