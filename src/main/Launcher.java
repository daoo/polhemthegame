/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package main;

import java.io.FileNotFoundException;

import loader.Cache;
import loader.CacheException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import other.Defines;
import other.Enviroment;
import ui.StateMenu;
import basics.Rectangle;
import credits.StateCredits;

public class Launcher extends StateBasedGame {
  public static final int  MAINMENU       = 0;
  public static final int  CREDITS        = 1;
  public static final int  GAMEPLAY       = 2;

  public static Cache      cache;

  private static final int DEFAULT_WIDTH  = 1024;
  private static final int DEFAULT_HEIGHT = 768;

  public static int        width          = Launcher.DEFAULT_WIDTH;
  public static int        height         = Launcher.DEFAULT_HEIGHT;
  public static boolean    fullscreen     = false;
  public static int        max_fps        = 100;
  public static Rectangle  rect           = new Rectangle(0, 0, width, height);

  public Launcher() throws SlickException {
    super(Defines.NAME + " - " + Defines.VERSION);

    addState(new StateMenu(Launcher.MAINMENU));
    addState(new StateGame(Launcher.GAMEPLAY));
    addState(new StateCredits(Launcher.CREDITS));
    enterState(Launcher.MAINMENU);
  }

  public static void main(final String[] args) {
    try {
      // TODO: Fix config
      Launcher.cache = new Cache(new Enviroment().appDir);

      final AppGameContainer app = new AppGameContainer(new Launcher());

      app.setDisplayMode(Launcher.width, Launcher.height, Launcher.fullscreen);
      app.start();
    } catch (final FileNotFoundException e) {
      e.printStackTrace();
    } catch (final SlickException e) {
      e.printStackTrace();
    } finally {
      try {
        Launcher.cache.close();
      } catch (final CacheException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void initStatesList(final GameContainer gameContainer)
    throws SlickException {
    getState(Launcher.MAINMENU).init(gameContainer, this);
    getState(Launcher.GAMEPLAY).init(gameContainer, this);
    getState(Launcher.CREDITS).init(gameContainer, this);
  }
}
