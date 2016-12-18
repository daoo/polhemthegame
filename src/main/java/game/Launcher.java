/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game;

import com.google.gson.Gson;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import game.config.Binds;
import game.config.Config;
import game.misc.Locator;
import game.states.StateManager;
import loader.Cache;
import loader.data.json.ConfigData;
import util.Random;

public class Launcher extends BasicGame {
  private static final String CONFIG_FILE = "config.js";

  private static final String NAME    = "PolhemTheGame";
  private static final String VERSION = "2.0";

  private static final int WIDTH   = 1024;
  private static final int HEIGHT  = 768;
  private static final int MAX_FPS = 60;

  private static final boolean FULLSCREEN = false;

  private StateManager stateGame;

  public Launcher() {
    super(NAME + " - " + VERSION);
  }

  public static void main(String[] args) throws SlickException, IOException {
    File workingDirectory = new File(System.getProperty("user.dir"));
    try (Cache cache = new Cache()) {
      final Config config;
      File configFile = new File(workingDirectory, CONFIG_FILE);
      if (configFile.exists()) {
        try (FileReader reader = new FileReader(configFile)) {
          ConfigData configData = new Gson().fromJson(reader, ConfigData.class);

          Binds binds1 = new Binds(
              configData.player1.walkUp, configData.player1.walkDown,
              configData.player1.walkLeft, configData.player1.walkRight,
              configData.player1.fire, configData.player1.previousWeapon,
              configData.player1.nextWeapon, configData.player1.buy,
              configData.player1.weapon0, configData.player1.weapon1,
              configData.player1.weapon2, configData.player1.weapon3,
              configData.player1.weapon4);
          Binds binds2 = new Binds(
              configData.player2.walkUp, configData.player2.walkDown,
              configData.player2.walkLeft, configData.player2.walkRight,
              configData.player2.fire, configData.player2.previousWeapon,
              configData.player2.nextWeapon, configData.player2.buy,
              configData.player2.weapon0, configData.player2.weapon1,
              configData.player2.weapon2, configData.player2.weapon3,
              configData.player2.weapon4);

          config = new Config(binds1, binds2);
        }
      } else {
        config = new Config(Binds.DEFAULT, Binds.DEFAULT);
      }

      Locator.registerConfig(config);
      Locator.registerCache(cache);
      Locator.registerRandom(new Random());

      AppGameContainer app = new AppGameContainer(new Launcher());

      app.setDisplayMode(WIDTH, HEIGHT, FULLSCREEN);
      app.start();
    }
  }

  @Override
  public void init(GameContainer container) throws SlickException {
    container.setTargetFrameRate(MAX_FPS);
    container.setVSync(true);

    stateGame = new StateManager(WIDTH, HEIGHT);
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
