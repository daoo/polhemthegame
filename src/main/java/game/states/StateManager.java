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

  private final int mWindowWidth;
  private final int mWindowHeight;

  private boolean mExit;
  private IState mCurrentState;

  public StateManager(int windowWidth, int windowHeight) {
    mWindowWidth = windowWidth;
    mWindowHeight = windowHeight;
    mExit = false;
  }

  public IState getCurrentState() {
    return mCurrentState;
  }

  public boolean shouldExit() {
    return mExit;
  }

  public void quit() {
    mExit = true;
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
      CampaignData data = Locator.getCache()
          .get(campaign, new GsonParser<>(Locator.getGson(), CampaignData.class));

      GameState state = new GameState(this, data, twoPlayer, mWindowWidth, mWindowHeight);
      if (DEBUG) {
        switchToState(new DebugState(mWindowWidth, state));
      } else {
        switchToState(state);
      }
    } catch (ParserException | IOException | SlickException ex) {
      handleException(ex);
    }
  }

  public void enterCredits() {
    try {
      switchToState(new StateCredits(mWindowWidth, mWindowHeight));
    } catch (SlickException ex) {
      handleException(ex);
    }
  }

  private void switchToState(IState newState) {
    if (mCurrentState != null) {
      mCurrentState.end(this);
    }

    mCurrentState = newState;
    mCurrentState.start(this);
  }
}
