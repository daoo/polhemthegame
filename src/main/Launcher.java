/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package main;

import java.io.File;
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
  public static final int       MAINMENU   = 0;
  public static final int       CREDITS    = 1;
  public static final int       GAMEPLAY   = 2;

  public static final int       WIDTH      = 1024;
  public static final int       HEIGHT     = 768;
  public static final boolean   FULLSCREEN = false;
  public static final int       MAX_FPS    = 100;
  public static final Rectangle RECT       = new Rectangle(0, 0, WIDTH, HEIGHT);

  public static Cache           cache;

  public Launcher(final boolean skipMenu) {
    super(Defines.NAME + " - " + Defines.VERSION);

    addState(new StateMenu(Launcher.MAINMENU));
    addState(new StateGame(Launcher.GAMEPLAY));
    addState(new StateCredits(Launcher.CREDITS));

    if (skipMenu) {
      enterState(Launcher.GAMEPLAY);
    } else {
      enterState(Launcher.MAINMENU);
    }
  }

  public static void main(final String[] args) {
    try {
      String libPath = new File(System.getProperty("java.library.path")).getAbsolutePath();
      System.setProperty("org.lwjgl.librarypath", libPath);

      Launcher.cache = new Cache(new Enviroment().appDir);

      boolean skipMenu = false;
      for (String s : args) {
        if (s.equals("-s")) {
          skipMenu = true;
        }
      }

      final AppGameContainer app = new AppGameContainer(new Launcher(skipMenu));

      app.setDisplayMode(Launcher.WIDTH, Launcher.HEIGHT, Launcher.FULLSCREEN);
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
