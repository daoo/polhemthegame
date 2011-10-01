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

  public static final int       MAINMENU   = 0;
  public static final int       CREDITS    = 1;
  public static final int       GAMEPLAY   = 2;

  public static final int       WIDTH      = 1024;
  public static final int       HEIGHT     = 768;
  public static final boolean   FULLSCREEN = false;
  public static final int       MAX_FPS    = 100;
  public static final Rectangle RECT       = new Rectangle(0, 0, WIDTH, HEIGHT);

  public Launcher(final boolean skipMenu) {
    super(NAME + " - " + VERSION);
  }

  public static void main(final String[] args) {
    try {
      Locator.registerCache(new Cache(new Enviroment().appDir));
      Locator.registerRandom(new Random());

      boolean skipMenu = false;
      for (String s : args) {
        if (s.equals("-s")) {
          skipMenu = true;
        }
      }

      final AppGameContainer app = new AppGameContainer(new Launcher(skipMenu));

      app.setDisplayMode(WIDTH, HEIGHT, FULLSCREEN);
      app.start();
    } catch (final FileNotFoundException e) {
      e.printStackTrace();
    } catch (final SlickException e) {
      e.printStackTrace();
    } finally {
      try {
        Locator.getCache().close();
      } catch (final CacheException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void render(GameContainer container, Graphics g) throws SlickException {

  }

  @Override
  public void init(GameContainer container) throws SlickException {

  }

  @Override
  public void update(GameContainer container, int delta) throws SlickException {

  }
}
