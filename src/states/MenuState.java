/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package states;

import game.CacheTool;

import java.io.IOException;
import java.util.LinkedList;

import loader.parser.ParserException;
import main.Locator;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ui.IUIEvent;
import ui.menu.Menu;
import ui.menu.MenuButton;
import ui.menu.MenuItem;
import ui.menu.MenuItemDisabled;
import util.Node;

public class MenuState implements IState {
  private final Image background;
  private final Menu  menu;

  public MenuState(final StateManager manager)
  throws ParserException, IOException {
    background = CacheTool.getImage(Locator.getCache(), "textures/menu/main.png");

    LinkedList<MenuItem> tmp = new LinkedList<>();

    tmp.add(new MenuButton("singleplayer", 60, 280, new IUIEvent() {
      @Override public void fire() {
        manager.enterSinglePlayer();
      }
    }));
    tmp.add(new MenuItemDisabled("coop", 60, 380));
    tmp.add(new MenuItemDisabled("options", 60, 480));
    tmp.add(new MenuButton("exit", 60, 580, new IUIEvent() {
      @Override public void fire() {
        manager.quit();
      }
    }));

    menu = new Menu(tmp);
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
  public void update(StateManager stateGame, float delta) {
    if (Keyboard.isKeyDown(Keyboard.KEY_F2)) {
      stateGame.quit();
    } else if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
      menu.up();
    } else if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
      menu.down();
    } else if (Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
      menu.click();
    }
  }

  @Override
  public void render(Graphics g) throws SlickException {
    g.drawImage(background, 0, 0);
    menu.render(g);
  }

  @Override
  public Node<Object> debugInfo() {
    return new Node<Object>("MenuState");
  }
}
