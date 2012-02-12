/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package states;

import game.CacheTool;

import java.io.IOException;
import java.util.ArrayList;

import loader.parser.ParserException;
import main.Key;
import main.Locator;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ui.IUIEvent;
import ui.menu.Menu;
import ui.menu.MenuButton;
import ui.menu.MenuItem;
import util.Node;

public class MenuState implements IState {
  private static final String CAMPAIGN_FILE = "campaigns/polhem.js";
  private static final String MENU_BACKGROUND_FILE = "textures/menu/main.png";

  private static final String BUTTON_SINGLE_PLAYER = "singleplayer";
  private static final String BUTTON_COOP = "coop";
  private static final String BUTTON_EXIT = "exit";

  private final Image background;
  private final Menu  menu;

  private final Key keyUp, keyDown, keyEnter;

  public MenuState(final StateManager manager)
  throws ParserException, IOException {
    background = CacheTool.getImage(Locator.getCache(), MENU_BACKGROUND_FILE);

    ArrayList<MenuItem> tmp = new ArrayList<>(4);

    tmp.add(new MenuButton(BUTTON_SINGLE_PLAYER, 60, 380, new IUIEvent() {
      @Override public void fire() {
        manager.enterGame(CAMPAIGN_FILE, false);
      }
    }));
    tmp.add(new MenuButton(BUTTON_COOP, 60, 480, new IUIEvent() {
      @Override public void fire() {
        manager.enterGame(CAMPAIGN_FILE, true);
      }
    }));
    tmp.add(new MenuButton(BUTTON_EXIT, 60, 580, new IUIEvent() {
      @Override public void fire() {
        manager.quit();
      }
    }));

    menu = new Menu(tmp);

    keyUp = new Key(Keyboard.KEY_UP);
    keyDown = new Key(Keyboard.KEY_DOWN);
    keyEnter = new Key(Keyboard.KEY_RETURN);
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
    if (Keyboard.isKeyDown(Keyboard.KEY_F2)) {
      stateGame.quit();
    }

    keyUp.update();
    if (keyUp.wasPressed()) {
      menu.up();
    }

    keyDown.update();
    if (keyDown.wasPressed()) {
      menu.down();
    }

    keyEnter.update();
    if (keyEnter.wasPressed()) {
      menu.click();
    }
  }

  @Override
  public void render(Graphics g) throws SlickException {
    g.drawImage(background, 0, 0);
    menu.render(g);
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
