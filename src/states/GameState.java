/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package states;

import game.types.GameTime;

import java.io.IOException;

import loader.data.json.CampaignData;
import loader.parser.ParserException;
import main.Key;
import main.LevelManager;
import main.Locator;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import ui.hud.BlackBox;
import ui.hud.PlayerUI;
import ui.hud.UI;
import util.Node;

public class GameState implements IState {
  private final UI ui;
  private final LevelManager levels;

  private final Key keyBackspace, keyEscape;

  private long elapsed;
  private boolean paused;

  private final int arenaX, arenaY, windowWidth, windowHeight;

  public GameState(StateManager stateManager, CampaignData data, boolean twoPlayer,
                   int windowWidth, int windowHeight)
      throws ParserException, IOException, SlickException {
    this.windowWidth = windowWidth;
    this.windowHeight = windowHeight;

    keyBackspace = new Key(Keyboard.KEY_BACK);
    keyEscape = new Key(Keyboard.KEY_ESCAPE);

    arenaX = 0;
    arenaY = PlayerUI.HEIGHT;

    int arenaWidth  = windowWidth;
    int arenaHeight = windowHeight - PlayerUI.HEIGHT * 2;

    ui = new UI(windowWidth, windowHeight);
    Locator.registerUI(ui);
    ui.addStatic(new BlackBox(0, 0, windowWidth, PlayerUI.HEIGHT));
    ui.addStatic(new BlackBox(0, windowHeight - PlayerUI.HEIGHT,
          windowWidth, PlayerUI.HEIGHT));

    levels = new LevelManager(stateManager, twoPlayer, data, windowWidth,
        arenaWidth, arenaHeight);

    elapsed = 0;
    paused = false;
  }

  @Override
  public void start(StateManager stateManager) {
    levels.nextLevel();
  }

  @Override
  public void end(StateManager stateManager) {
    // Do nothing
  }

  /**
   * The update iteration of the game loop.
   * Updates entities, switches levels, etc.
   *
   * @param dt Approximate length of the current frame in milliseconds
   */
  @Override
  public void update(StateManager stateManager, int dt) {
    if (paused) {
      keyBackspace.update();
      keyEscape.update();
      if (keyEscape.wasPressed()) {
        paused = false;
      } else if (keyBackspace.wasPressed()) {
        stateManager.enterMainMenu();
      }
    } else {
      keyEscape.update();
      if (keyEscape.wasPressed()) {
        paused = true;
      } else {
        elapsed += dt;
        levels.update(new GameTime(dt / 1000.0f, dt, elapsed));
        ui.update();

        if (levels.isFinished()) {
          if (levels.isCredits()) {
            stateManager.enterCredits();
          } else {
            stateManager.enterMainMenu();
          }
        }
      }
    }
  }

  @Override
  public void render(Graphics g) {
    g.pushTransform();
    g.translate(arenaX, arenaY);

    levels.render(g);
    ui.renderDynamics(g);

    g.popTransform();

    ui.renderStatics(g);

    if (paused) {
      g.setColor(new Color(0, 0, 0, 100));
      g.fillRect(0, 0, windowWidth, windowHeight);
    }
  }

  @Override
  public String debugString() {
    return "GameState, time " + String.valueOf(elapsed);
  }

  @Override
  public Node<String> debugTree() {
    Node<String> parent = new Node<>(debugString());
    parent.nodes.add(levels.debugTree());

    return parent;
  }
}
