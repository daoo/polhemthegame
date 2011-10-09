/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package main;


import java.io.FileNotFoundException;

import loader.Cache;
import loader.CacheException;
import math.Random;
import math.Rectangle;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


public class Launcher extends BasicGame {
  public static final String NAME    = "PolhemTheGame";
  public static final String VERSION = "1.0";

  public static final int       WIDTH      = 1024;
  public static final int       HEIGHT     = 768;
  public static final boolean   FULLSCREEN = false;
  public static final int       MAX_FPS    = 100;
  public static final Rectangle RECT       = new Rectangle(0, 0, WIDTH, HEIGHT);

  private GameStateManager stateGame;

  public Launcher() {
    super(NAME + " - " + VERSION);
  }

  public static void main(final String[] args) {
    try {
      Locator.registerCache(new Cache(new Enviroment().appDir));
      Locator.registerRandom(new Random());

      final AppGameContainer app = new AppGameContainer(new Launcher());

      app.setDisplayMode(WIDTH, HEIGHT, FULLSCREEN);
      app.start();
    } catch (final FileNotFoundException ex) {
      ex.printStackTrace();
    } catch (final SlickException ex) {
      ex.printStackTrace();
    } finally {
      try {
        Locator.getCache().close();
      } catch (final CacheException ex) {
        ex.printStackTrace();
      }
    }
  }

  @Override
  public void init(final GameContainer container) throws SlickException {
    container.setTargetFrameRate(60);

    stateGame = new GameStateManager();
    stateGame.enterMainMenu();
  }

  @Override
  public void render(final GameContainer container, final Graphics g) throws SlickException {
    stateGame.getCurrentState().render(g);
  }

  @Override
  public void update(final GameContainer container, final int delta) throws SlickException {
    if (stateGame.shouldExit()) {
      container.exit();
    } else {
      stateGame.getCurrentState().update(stateGame, delta / 1000.0f);
    }
  }
}
