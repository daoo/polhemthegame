/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package main;

import java.io.IOException;

import loader.data.DataException;
import loader.parser.ParserException;
import ui.MenuState;

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

  public void enterMainMenu() {
    try {
      currentState = new MenuState(this);
    } catch (ParserException e) {
      e.printStackTrace();
      quit();
    } catch (IOException e) {
      e.printStackTrace();
      quit();
    }
  }

  public void enterSinglePlayer() {
    try {
      currentState = new GameState();
    } catch (ParserException ex) {
      ex.printStackTrace();
      quit();
    } catch (DataException ex) {
      ex.printStackTrace();
      quit();
    } catch (IOException ex) {
      ex.printStackTrace();
      quit();
    }
  }
}
