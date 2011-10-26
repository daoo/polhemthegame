/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.modes;

import game.Campaign;
import game.Level;
import game.entities.Players;
import game.time.GameTime;

import java.io.IOException;

import loader.data.DataException;
import loader.data.json.CampaignData;
import loader.parser.ParserException;
import main.Locator;
import math.Rectangle;

import org.newdawn.slick.Graphics;

import states.StateManager;
import ui.hud.ShopUI;
import ui.hud.UI;

public class GameMode implements IMode {
  private final UI ui;

  private final Campaign campaign;
  private Level level;

  private final Players players;

  /**
   * Describes the entire area availible to the game (non-ui stuff).
   * Relative to the upper left corner of the game window.
   */
  private final Rectangle arenaRect;

  /**
   * How much time have elapsed since we started.
   */
  private float elapsed;

  public GameMode(CampaignData data, int width, int height)
      throws IOException, ParserException, DataException {
    if (data.levels.isEmpty()) {
      throw new IllegalArgumentException("No levels in campaing");
    }

    elapsed   = 0;
    arenaRect = new Rectangle(0, ShopUI.HEIGHT, width, height - ShopUI.HEIGHT * 2);
    campaign  = new Campaign(data);
    players   = new Players(1); // TODO: Coop

    campaign.nextLevel();
    level = new Level(
      campaign.getCurrentLevel(),
      players,
      arenaRect.getWidth(),
      arenaRect.getHeight()
    );

    ui = new UI();
    Locator.registerUI(ui);
  }

  private void nextLevel(StateManager stateManager)
      throws DataException, ParserException, IOException {
    if (campaign.hasMoreLevels()) {
      campaign.nextLevel();
      level = new Level(
        campaign.getCurrentLevel(),
        players,
        arenaRect.getWidth(),
        arenaRect.getHeight()
      );
    } else {
      // TODO: Credits
      // stateManager.enterCredits();
    }
  }

  /**
   * The update iteration of the game loop.
   * Updates entities, switches levels, etc.
   *
   * @param dt Approximate length of the current frame in seconds.
   */
  @Override
  public void update(float dt) {
    if (level.isFinished()) {
      try {
        nextLevel();
      } catch (DataException ex) {
        ex.printStackTrace();
        System.exit(1);
      } catch (ParserException ex) {
        ex.printStackTrace();
        System.exit(1);
      } catch (IOException ex) {
        ex.printStackTrace();
        System.exit(1);
      }
    } else {
      elapsed += dt;
      level.update(new GameTime(dt, elapsed));
      ui.update();
    }
  }

  @Override
  public void render(Graphics g) {
    g.pushTransform();
    g.translate(arenaRect.getX1(), arenaRect.getY1());

    level.render(g);
    ui.render(g);

    g.popTransform();
  }
}
