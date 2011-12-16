/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package main;

import java.io.IOException;

import loader.Cache;
import math.Random;
import math.Rectangle;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import states.StateManager;


public class Launcher extends BasicGame {
  public static final String NAME    = "PolhemTheGame";
  public static final String VERSION = "1.0";

  public static final int       WIDTH      = 1024;
  public static final int       HEIGHT     = 768;
  public static final boolean   FULLSCREEN = false;
  public static final int       MAX_FPS    = 60;
  public static final Rectangle RECT       = new Rectangle(0, 0, WIDTH, HEIGHT);

  private StateManager stateGame;

  public Launcher() {
    super(NAME + " - " + VERSION);
  }

  public static void main(String[] args) {
    try (Cache cache = new Cache(new Enviroment().appDir)) {
      Locator.registerCache(cache);
      Locator.registerRandom(new Random());

      AppGameContainer app = new AppGameContainer(new Launcher());

      app.setDisplayMode(WIDTH, HEIGHT, FULLSCREEN);
      app.start();
    } catch (SlickException | IOException ex) {
      ex.printStackTrace();
    }
  }

  @Override
  public void init(GameContainer container) throws SlickException {
    container.setTargetFrameRate(MAX_FPS);

    stateGame = new StateManager();
    stateGame.enterMainMenu();
  }

  @Override
  public void render(GameContainer container, Graphics g) throws SlickException {
    stateGame.getCurrentState().render(g);
  }

  @Override
  public void update(GameContainer container, int delta) throws SlickException {
    if (stateGame.shouldExit()) {
      container.exit();
    } else {
      stateGame.getCurrentState().update(stateGame, delta / 1000.0f);
    }
  }
}
