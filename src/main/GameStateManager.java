package main;

import java.io.IOException;

import loader.parser.ParserException;
import ui.MenuState;

public class GameStateManager {
  private boolean exit;

  private IGameState currentState;

  public GameStateManager() {
    exit = false;
  }

  public IGameState getCurrentState() {
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
    // TODO Auto-generated method stub
  }
}
