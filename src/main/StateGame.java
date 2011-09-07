/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package main;

import game.modes.Game;
import game.modes.IMode;
import game.ui.HUD;

import java.io.IOException;

import loader.data.DataException;
import loader.data.json.CampaignData;
import loader.parser.GsonParser;
import loader.parser.ParserException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class StateGame extends BasicGameState {
  private int     stateID = 0;

  private IMode   mode;

  private boolean exit;

  private final float timescale = 1.0f;

  public StateGame(final int id) {
    super();
    stateID = id;
  }

  @Override
  public int getID() {
    return stateID;
  }

  @Override
  public void init(final GameContainer gc, final StateBasedGame sb) {
    gc.setVerbose(false);
    gc.setClearEachFrame(true);
  }

  @Override
  public void enter(final GameContainer gc, final StateBasedGame sb)
    throws SlickException {
    try {
      final CampaignData campaign = (CampaignData)
        Locator.getCache().getCold(
          "campaigns/polhem.js",
          new GsonParser(CampaignData.class));

      final Game game = new Game(campaign, 0, HUD.HEIGHT,
                                 App.WIDTH,
                                 App.HEIGHT - (2 * HUD.HEIGHT));
      mode = game;
      //mode = new Debugger(game);

    } catch (final NumberFormatException e) {
      e.printStackTrace();
      gc.exit();
    } catch (final IOException e) {
      e.printStackTrace();
      gc.exit();
    } catch (final ParserException e) {
      e.printStackTrace();
      gc.exit();
    } catch (final DataException e) {
      e.printStackTrace();
      gc.exit();
    }
  }

  @Override
  public void render(final GameContainer gc, final StateBasedGame sb,
                     final Graphics g) throws SlickException {
    mode.render(g);
  }

  @Override
  public void update(final GameContainer gc, final StateBasedGame sb,
                     final int delta) throws SlickException {
    if (exit) {
      gc.exit();
    }

    if (mode.isFinished()) {
      sb.enterState(App.CREDITS);
    }
    else {
      mode.update((delta / 1000.0f) * timescale );
    }
  }

  @Override
  public void keyPressed(final int key, final char c) {
    if (key == Input.KEY_F2) {
      exit = true;
    }
  }
}
