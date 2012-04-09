/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package states;


import java.io.IOException;

import loader.data.json.CampaignData;
import loader.parser.GsonParser;
import loader.parser.ParserException;
import main.Locator;

import org.newdawn.slick.SlickException;

public class StateManager {
  private final int windowWidth, windowHeight;

  private boolean exit;
  private IState currentState;

  public StateManager(int windowWidth, int windowHeight) {
    this.windowWidth = windowWidth;
    this.windowHeight = windowHeight;
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
      CampaignData data = (CampaignData) Locator.getCache().get(
        campaign, new GsonParser(CampaignData.class));

      GameState state = new GameState(this, data, twoPlayer, windowWidth, windowHeight);
      switchToState(new DebugState(windowWidth, windowHeight, state));
    } catch (ParserException | IOException | SlickException ex) {
      handleException(ex);
    }
  }

  public void enterCredits() {
    try {
      switchToState(new StateCredits(windowWidth, windowHeight));
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
