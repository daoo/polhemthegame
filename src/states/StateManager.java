/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package states;


import java.io.IOException;

import loader.data.DataException;
import loader.data.json.CampaignData;
import loader.parser.GsonParser;
import loader.parser.ParserException;
import main.Launcher;
import main.Locator;

import org.newdawn.slick.SlickException;

public class StateManager {
  private boolean exit;

  private IState currentState;

  public StateManager() {
    exit = false;
  }

  public IState getCurrentState() {
    return currentState;
  }

  public boolean shouldExit() {
    return exit;
  }

  public void quit() {
    exit = true;
  }

  public void handleException(Exception ex) {
    ex.printStackTrace();
    quit();
  }

  public void enterMainMenu() {
    try {
      switchToState(new MenuState(this));
    } catch (ParserException | IOException ex) {
      handleException(ex);
    }
  }

  public void enterSinglePlayer() {
    try {
      CampaignData campaign = (CampaignData) Locator.getCache().getCold(
        "campaigns/polhem.js",
        new GsonParser(CampaignData.class)
      );

      switchToState(new GameState(campaign, Launcher.WIDTH, Launcher.HEIGHT));
    } catch (ParserException | DataException | IOException | SlickException ex) {
      handleException(ex);
    }
  }

  public void enterCredits() {
    try {
      switchToState(new StateCredits());
    } catch (ParserException | SlickException | IOException ex) {
      handleException(ex);
    }
  }

  private void switchToState(IState newState) {
    if (currentState != null)
      currentState.end(this);

    currentState = newState;
    currentState.start(this);
  }
}
