/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.states;


import org.newdawn.slick.SlickException;

import java.io.IOException;

import game.misc.Locator;
import loader.data.json.CampaignData;
import loader.parser.GsonParser;
import loader.parser.ParserException;

public class StateManager {
  private static final boolean DEBUG = false;

  private final int windowWidth;
  private final int windowHeight;

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
      if (DEBUG) {
        switchToState(new DebugState(windowWidth, state));
      } else {
        switchToState(state);
      }
    } catch (ParserException | IOException | SlickException ex) {
      handleException(ex);
    }
  }

  public void enterCredits() {
    try {
      switchToState(new StateCredits(windowWidth, windowHeight));
    } catch (SlickException ex) {
      handleException(ex);
    }
  }

  private void switchToState(IState newState) {
    if (currentState != null) {
      currentState.end(this);
    }

    currentState = newState;
    currentState.start(this);
  }
}
