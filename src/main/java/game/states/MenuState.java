/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.states;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.io.IOException;
import java.util.ArrayList;

import game.misc.CacheTool;
import game.misc.Locator;
import game.ui.menu.Menu;
import game.ui.menu.MenuItem;
import loader.parser.ParserException;
import util.Key;
import util.Node;

public class MenuState implements GameState {
  private static final String CAMPAIGN_FILE = "campaigns/polhem.js";
  private static final String MENU_BACKGROUND_FILE = "textures/menu/main.png";

  private static final String BUTTON_SINGLE_PLAYER = "singleplayer";
  private static final String BUTTON_COOP = "coop";
  private static final String BUTTON_EXIT = "exit";

  private final Image mBackground;
  private final Menu mMenu;

  private final Key mKeyUp;
  private final Key mKeyDown;
  private final Key mKeyEnter;

  public MenuState(final StateManager manager) throws ParserException, IOException {
    mBackground = CacheTool.getImage(Locator.getCache(), MENU_BACKGROUND_FILE);

    ArrayList<MenuItem> tmp = new ArrayList<>(4);

    Runnable enterOnePlayer = () -> manager.enterGame(CAMPAIGN_FILE, false);
    Runnable enterTwoPlayer = () -> manager.enterGame(CAMPAIGN_FILE, true);
    tmp.add(new MenuItem(BUTTON_SINGLE_PLAYER, 60, 380, enterOnePlayer));
    tmp.add(new MenuItem(BUTTON_COOP, 60, 480, enterTwoPlayer));
    tmp.add(new MenuItem(BUTTON_EXIT, 60, 580, manager::quit));

    mMenu = new Menu(tmp);

    mKeyUp = new Key(Keyboard.KEY_UP);
    mKeyDown = new Key(Keyboard.KEY_DOWN);
    mKeyEnter = new Key(Keyboard.KEY_RETURN);
  }

  @Override
  public void start(StateManager stateManager) {
    // Do nothing
  }

  @Override
  public void end(StateManager stateManager) {
    // Do nothing
  }

  @Override
  public void update(StateManager stateGame, int delta) {
    mKeyUp.update();
    if (mKeyUp.wasPressed()) {
      mMenu.up();
    }

    mKeyDown.update();
    if (mKeyDown.wasPressed()) {
      mMenu.down();
    }

    mKeyEnter.update();
    if (mKeyEnter.wasPressed()) {
      mMenu.click();
    }
  }

  @Override
  public void render(Graphics g) throws SlickException {
    g.drawImage(mBackground, 0, 0);
    mMenu.render(g);
  }

  @Override
  public String debugString() {
    return "MenuState";
  }

  @Override
  public Node<String> debugTree() {
    return new Node<>(debugString());
  }
}
