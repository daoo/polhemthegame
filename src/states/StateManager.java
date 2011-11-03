/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package states;

import java.io.IOException;

import loader.data.DataException;
import loader.parser.ParserException;

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
      currentState = new MenuState(this);
    } catch (ParserException ex) {
      handleException(ex);
    } catch (IOException ex) {
      handleException(ex);
    }
  }

  public void enterSinglePlayer() {
    try {
      currentState = new GameState();
      currentState.start(this);
    } catch (ParserException ex) {
      handleException(ex);
    } catch (DataException ex) {
      handleException(ex);
    } catch (IOException ex) {
      handleException(ex);
    }
  }

  public void enterCredits() {
    try {
      currentState = new StateCredits();
    } catch (ParserException ex) {
      handleException(ex);
    } catch (SlickException ex) {
      handleException(ex);
    } catch (IOException ex) {
      handleException(ex);
    }
  }
}
