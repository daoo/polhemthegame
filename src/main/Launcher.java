/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package main;

import game.types.Binds;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import loader.Cache;
import loader.data.json.ConfigData;
import math.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import states.StateManager;

import com.google.gson.Gson;

public class Launcher extends BasicGame {
  private static final String CONFIG_FILE = "config.js";

  private static final String NAME    = "PolhemTheGame";
  private static final String VERSION = "2.0";

  public static final int WIDTH   = 1024;
  public static final int HEIGHT  = 768;
  public static final int MAX_FPS = 60;

  public static final boolean FULLSCREEN = false;

  private StateManager stateGame;

  public Launcher() {
    super(NAME + " - " + VERSION);
  }

  public static void main(String[] args) {
    Enviroment env = new Enviroment();
    try (Cache cache = new Cache(env.appDir)) {
      Config config = null;
      File configFile = new File(env.appDir, CONFIG_FILE);
      if (configFile.exists()) {
        try (FileReader reader = new FileReader(configFile)) {
          ConfigData configData = new Gson().fromJson(reader, ConfigData.class);

          config = new Config(
              new Binds(configData.player1.walkUp, configData.player1.walkDown,
                        configData.player1.walkLeft, configData.player1.walkRight,
                        configData.player1.fire, configData.player1.nextWeapon,
                        configData.player1.buy),
              new Binds(configData.player2.walkUp, configData.player2.walkDown,
                        configData.player2.walkLeft, configData.player2.walkRight,
                        configData.player2.fire, configData.player2.nextWeapon,
                        configData.player2.buy));
        }
      } else {
        config = new Config(new Binds(), new Binds());
      }

      Locator.registerConfig(config);
      Locator.registerCache(cache);
      Locator.registerRandom(new Random());

      AppGameContainer app = new AppGameContainer(new Launcher());

      app.setDisplayMode(WIDTH, HEIGHT, FULLSCREEN);
      app.start();

      Locator.close();
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
      stateGame.getCurrentState().update(stateGame, delta);
    }
  }
}
