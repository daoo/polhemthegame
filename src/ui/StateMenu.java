/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package ui;

import java.io.IOException;

import loader.parser.ParserException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import ptg.App;
import ptg.CacheTool;
import ptg.Locator;
import ui.menu.Menu;
import ui.menu.MenuButton;
import ui.menu.MenuItemDisabled;

public class StateMenu extends BasicGameState {
  private final int stateId;
  private Image     background;
  private Menu      menu;

  class singleplayer implements IUiEvent {
    final StateBasedGame sb;

    public singleplayer(final StateBasedGame sb) {
      this.sb = sb;
    }

    @Override
    public void fire() {
      sb.enterState(App.GAMEPLAY);
    }
  }

  class exit implements IUiEvent {
    final GameContainer gc;

    public exit(final GameContainer gc) {
      this.gc = gc;
    }

    @Override
    public void fire() {
      gc.exit();
    }
  }

  public StateMenu(final int stateId) {
    super();
    this.stateId = stateId;
  }

  @Override
  public int getID() {
    return stateId;
  }

  @Override
  public void init(final GameContainer gc, final StateBasedGame sb)
    throws SlickException {
    gc.setVerbose(false);
  }

  @Override
  public void enter(final GameContainer gc, final StateBasedGame sb)
    throws SlickException {
    gc.setClearEachFrame(true);
    gc.setTargetFrameRate(App.MAX_FPS);

    try {
      background = CacheTool.getImage(Locator.getCache(), "textures/menu/main.png");

      menu = new Menu();

      menu.add(new MenuButton("singleplayer", 60, 280, new singleplayer(sb)));
      menu.add(new MenuItemDisabled("coop", 60, 380));
      menu.add(new MenuItemDisabled("options", 60, 480));
      menu.add(new MenuButton("exit", 60, 580, new exit(gc)));
    } catch (final IOException e) {
      e.printStackTrace();
    } catch (final ParserException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update(final GameContainer gc, final StateBasedGame sb, final int delta)
    throws SlickException {
    // Nothing to do here
  }

  @Override
  public void render(final GameContainer gc, final StateBasedGame sb,
                     final Graphics g) throws SlickException {
    if ((background != null) && (menu != null)) {
      g.drawImage(background, 0, 0);
      menu.render(g);
    }
  }

  @Override
  public void keyPressed(final int key, final char c) {
    if (key == Input.KEY_F2)
      System.exit(0);
    else if (key == Input.KEY_UP)
      menu.up();
    else if (key == Input.KEY_DOWN)
      menu.down();
    else if (key == Input.KEY_ENTER)
      menu.click();
  }
}
