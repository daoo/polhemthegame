/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package states;

import game.CacheTool;
import game.Campaign;
import game.entities.Players;
import game.factories.WorldFactory;
import game.pods.GameTime;
import game.world.World;

import java.io.IOException;

import loader.data.DataException;
import loader.data.json.CampaignData;
import loader.parser.ParserException;
import main.Locator;
import math.Rectangle;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ui.hud.PlayerUI;
import ui.hud.UI;

public class GameState implements IState {
  private enum ACTION { NEXT_LEVEL, MAIN_MENU, CREDITS }
  private ACTION nextAction;

  private final UI ui;

  private final Campaign campaign;
  private final Image background, statics;
  private World world;

  private final Players players;

  private final WorldFactory worldFactory;

  /**
   * Describes the entire area availible to the game (non-ui stuff).
   * Relative to the upper left corner of the game window.
   */
  private final Rectangle arenaRect;

  /**
   * The rectangle area availible for the world. Relative to the arenaRect.
   */
  private final Rectangle worldRect;

  /**
   * How much time have elapsed since we started.
   */
  private float elapsed;

  public GameState(CampaignData data, int windowWidth, int windowHeight)
      throws ParserException, DataException, IOException, SlickException {
    if (data.levels.isEmpty()) {
      throw new IllegalArgumentException("No levels in campaign");
    }

    ui = new UI();
    Locator.registerUI(ui);

    int arenaWidth = windowWidth;
    int arenaHeight = windowHeight - PlayerUI.HEIGHT * 2;
    arenaRect = new Rectangle(0, PlayerUI.HEIGHT, arenaWidth, arenaHeight);

    campaign = new Campaign(data);
    background = CacheTool.getImage(Locator.getCache(), data.background);
    statics = new Image(arenaWidth, arenaHeight);

    float left   = data.constraints[0];
    float top    = data.constraints[1];
    float bottom = data.constraints[2];
    float right  = data.constraints[3];

    worldRect = new Rectangle(
      left, top,
      arenaWidth - left - right,
      arenaHeight - top - bottom
    );

    elapsed = 0;
    players = new Players(1, worldRect); // TODO: Coop

    worldFactory = new WorldFactory(worldRect, players);
  }

  @Override
  public void start(StateManager stateManager) {
    try {
      campaign.nextLevel();

      world = worldFactory.makeLevel(this, campaign.getCurrentLevel());
    } catch (ParserException | IOException | DataException ex) {
      stateManager.handleException(ex);
    }
  }

  @Override
  public void end(StateManager stateManager) {
    // Do nothing
  }

  /**
   * The update iteration of the game loop.
   * Updates entities, switches levels, etc.
   *
   * @param dt Approximate length of the current frame in seconds.
   */
  @Override
  public void update(StateManager stateManager, float dt) {
    elapsed += dt;
    world.update(new GameTime(dt, elapsed));
    ui.update();

    if (nextAction == ACTION.NEXT_LEVEL) {
      try {
        nextLevel();
      } catch (DataException | ParserException | IOException ex) {
        stateManager.handleException(ex);
      }
      nextAction = null;
    } else if (nextAction == ACTION.CREDITS) {
      stateManager.enterCredits();
      nextAction = null;
    } else if (nextAction == ACTION.MAIN_MENU) {
      stateManager.enterMainMenu();
      nextAction = null;
    }
  }

  @Override
  public void render(Graphics g) {
    g.pushTransform();
    g.translate(arenaRect.getX1(), arenaRect.getY1());

    g.drawImage(background, 0, 0);
    g.drawImage(statics, 0, 0);
    world.render(g);
    ui.renderDynamics(g);

    g.popTransform();

    ui.renderStatics(g);
  }

  private void nextLevel()
      throws DataException, ParserException, IOException {
    if (campaign.hasMoreLevels()) {
      campaign.nextLevel();

      world = worldFactory.makeLevel(this, campaign.getCurrentLevel());
    } else {
      goCredits();
    }
  }

  public void goMainMenu() {
    nextAction = ACTION.MAIN_MENU;
  }

  public void goCredits() {
    nextAction = ACTION.CREDITS;
  }

  public void goNextLevel() {
    nextAction = ACTION.NEXT_LEVEL;
  }
}
