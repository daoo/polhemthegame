/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package states;


import java.io.IOException;

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

  public void enterGame(String campaign, boolean twoPlayer) {
    try {
      CampaignData data = (CampaignData) Locator.getCache().getCold(
        campaign, new GsonParser(CampaignData.class));

      GameState state = new GameState(this, data, twoPlayer, Launcher.WIDTH, Launcher.HEIGHT);
      switchToState(new DebugState(state));
    } catch (ParserException | IOException | SlickException ex) {
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
