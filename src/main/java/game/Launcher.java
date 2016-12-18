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
import java.io.FileNotFoundException;
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
  private static final int WIDTH   = 1024;
  private static final int HEIGHT  = 768;
  private static final int MAX_FPS = 60;

  private static final boolean FULLSCREEN = false;

  private StateManager stateGame;

  private Launcher() {
    super("PolhemTheGame");
  }

  private static Config readConfig() throws IOException {
    File workingDirectory = new File(System.getProperty("user.dir"));
    File configFile = new File(workingDirectory, "config.js");
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

      return new Config(binds1, binds2);
    } catch (FileNotFoundException ignored) {
      return new Config(Binds.DEFAULT, Binds.DEFAULT);
    }
  }

  public static void main(String[] args) throws SlickException, IOException {
    try (Cache cache = new Cache()) {
      Locator.registerConfig(readConfig());
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
