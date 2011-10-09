package main;

import java.io.IOException;

import loader.data.DataException;
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
    } catch (final ParserException e) {
      e.printStackTrace();
      quit();
    } catch (final IOException e) {
      e.printStackTrace();
      quit();
    }
  }

  public void enterSinglePlayer() {
    try {
      currentState = new GameRound();
    } catch (final ParserException ex) {
      ex.printStackTrace();
      quit();
    } catch (final DataException ex) {
      ex.printStackTrace();
      quit();
    } catch (final IOException ex) {
      ex.printStackTrace();
      quit();
    }
  }
}
